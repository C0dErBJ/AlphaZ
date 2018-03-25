package com.alphaz.core.config

import com.alphaz.core.authorization.user.UserService
import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.constant.Status
import com.alphaz.infrastructure.domain.model.ErrorInfo
import com.alphaz.infrastructure.domain.model.ResponseModel
import com.alphaz.infrastructure.util.JsonHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 *@Author: c0der
 *@Date: 下午1:20 2018/3/20
 *@Description:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    private val REST_CONTENT_TYPE = "application/json;application/x-www-form-urlencoded;multipart/form-data"
    @Autowired
    private val userService: UserService? = null
    @Autowired
    private val localizationService: LocalizationService? = null

    open override fun configure(web: WebSecurity?) {
        //此处配置静态资源文件过滤
        web!!.ignoring().antMatchers(
                "/favicon.ico",
                "/system/**",
                "/error",
                "/image",
                "/resources/**",
                "/script/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**",
                "/privilege"
        )
    }

    @Throws(Exception::class)
    open override fun configure(http: HttpSecurity) {
        // 此处配置服务资源过滤，基于权限
        // Warn 此处貌似有个坑，禁止匿名访问后会影响到formlogin使之不能正常使用，会影响到不能获取principle，是不是安全的机制还未确定
        http.formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler { _, response, _ ->

                    response.contentType = "application/json;charset=utf-8"
                    response.writer.write(JsonHelper.toString(ResponseModel<Any>()))
                }
                .failureHandler { _, response, _ ->
                    response.contentType = "application/json;charset=utf-8"
                    response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(localizationService!!.getMessage("loginFail")))))
                }.and()
                //请求中带remember-me参数
                //                .rememberMe().rememberMeCookieDomain("alphaz.com").rememberMeCookieName("rememberme.alphaz").tokenRepository().and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint { request, response, _ ->
                    if (request.contentType == null || REST_CONTENT_TYPE.contains(request.contentType.toLowerCase())) {
                        response.contentType = "application/json;charset=utf-8"
                        response.writer.write(JsonHelper.toString(ResponseModel<Any>(Status.FAILED, ErrorInfo(localizationService!!.getMessage("needAuthorization")), "401")))
                    } else {
                        response.sendRedirect("/login")
                    }
                }.accessDeniedHandler { request, response, accessDeniedException ->
                    response.contentType = "application/json;charset=utf-8"
                    response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(localizationService!!.getMessage("accessDenied")))))
                }.and()
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .csrf()
                .disable()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Autowired
    @Throws(Exception::class)
    open override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService<UserService>(userService).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    open override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}