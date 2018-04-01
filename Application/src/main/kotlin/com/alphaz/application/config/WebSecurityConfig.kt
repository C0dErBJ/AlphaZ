package com.alphaz.application.config

import com.alphaz.core.authorization.user.UserPolicy
import com.alphaz.core.authorization.user.UserRepository
import com.alphaz.core.authorization.user.UserService
import com.alphaz.core.authorization.user.UserSignInRecord
import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.constant.enums.Status
import com.alphaz.infrastructure.domain.model.common.ErrorInfo
import com.alphaz.infrastructure.domain.model.common.ResponseModel
import com.alphaz.infrastructure.util.HttpHelper
import com.alphaz.infrastructure.util.JsonHelper
import org.greenrobot.eventbus.EventBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import java.time.LocalDateTime

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
    private lateinit var userService: UserService
    @Autowired
    private lateinit var userPolicy: UserPolicy
    @Autowired
    private lateinit var userRepository: UserRepository;
    @Autowired
    private lateinit var eventBus: EventBus
    @Autowired
    private lateinit var l: LocalizationService

    companion object {
        const val userNameParameterName = "username";
        const val passwordParameterName = "password";
        const val loginUrl = "/login";
        const val defaultContentType = "application/json;charset=utf-8"
    }

    open override fun configure(web: WebSecurity) {
        //此处配置静态资源文件过滤
        web.ignoring().antMatchers(
                "/favicon.ico",
                "/system/**",
                "/error",
                "/image",
                "/resources/**",
                "/script/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**"
        )
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // 此处配置服务资源过滤，基于权限
        // Warn 此处貌似有个坑，禁止匿名访问后会影响到formlogin使之不能正常使用，会影响到不能获取principle，是不是安全的机制还未确定
        http.formLogin().loginPage(loginUrl)
                .usernameParameter(userNameParameterName).passwordParameter(passwordParameterName)
                .successHandler { request, response, _ ->
                    val username = request.getParameter(userNameParameterName);
                    if (username != null) {
                        eventBus.post(UserSignInRecord(null, username, HttpHelper.getClientIP(request), LocalDateTime.now(), true));
                    }
                    response.contentType = defaultContentType
                    response.writer.write(JsonHelper.toString(ResponseModel<Any>()))
                }
                .failureHandler { request, response, exception ->
                    //登录失败控制账号锁定的逻辑
                    val username = request.getParameter(userNameParameterName);
                    if (username != null) {
                        eventBus.post(UserSignInRecord(null, username, HttpHelper.getClientIP(request), LocalDateTime.now(), false));
                    }
                    if (username != null) {
                        val user = userRepository.findFirstByUsername(username);
                        if (user != null) {
                            user.loginFailCount++;
                            userPolicy.userSignInPolicy(user)
                            userRepository.save(user)
                        }
                    }
                    response.contentType = defaultContentType
                    when (exception) {
                        is BadCredentialsException -> {
                            response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(l.getMessage("loginFail")))))
                        }
                        is LockedException -> {
                            response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(l.getMessage("accountIsLocked")))))
                        }
                        is AccountExpiredException -> {
                            response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(l.getMessage("accountIsExpired")))))
                        }
                        else -> {
                            response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(l.getMessage("loginFail")))))
                        }
                    }
                }.and()
                //请求中带remember-me参数
                //                .rememberMe().rememberMeCookieDomain("alphaz.com").rememberMeCookieName("rememberme.alphaz").tokenRepository().and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(loginUrl).permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint { request, response, _ ->
                    if (request.contentType == null || REST_CONTENT_TYPE.contains(request.contentType.toLowerCase())) {
                        response.contentType = defaultContentType
                        response.writer.write(JsonHelper.toString(ResponseModel<Any>(Status.FAILED, ErrorInfo(l.getMessage("needAuthorization")), "401")))
                    } else {
                        response.sendRedirect(loginUrl)
                    }
                }.accessDeniedHandler { _, response, _ ->
                    response.contentType = defaultContentType
                    response.writer.write(JsonHelper.toString(ResponseModel<Any>(ErrorInfo(l.getMessage("accessDenied")))))
                }.and()
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .csrf()
                .disable()
    }


    @Throws(Exception::class)
    override fun configure(@Autowired auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserService>(userService).passwordEncoder(userPolicy.passwordEncoder)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}