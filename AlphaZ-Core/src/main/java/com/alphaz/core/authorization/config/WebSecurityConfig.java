package com.alphaz.core.authorization.config;

import com.alphaz.core.authorization.service.UserService;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.LocalizationService;
import com.alphaz.util.string.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: c0der
 * @Date: 下午3:45 2018/1/24
 * @Description:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final static String REST_CONTENT_TYPE = "application/json;application/x-www-form-urlencoded;multipart/form-data";
    @Autowired
    private UserService userService;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public void configure(WebSecurity web) {
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
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 此处配置服务资源过滤，基于权限
        // Warn 此处貌似有个坑，禁止匿名访问后会影响到formlogin使之不能正常使用，会影响到不能获取principle，是不是安全的机制还未确定
        http.formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.Ava, localizationService.getMessage("loginSuccess"))));
                })
                .failureHandler((request, response, authentication) -> {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.NAva, localizationService.getMessage("loginFail"))));
                        }
                ).and()
                //请求中带remember-me参数
//                .rememberMe().rememberMeCookieDomain("alphaz.com").rememberMeCookieName("rememberme.alphaz").tokenRepository().and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            if (request.getContentType() == null || REST_CONTENT_TYPE.contains(request.getContentType().toLowerCase())) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.NAva, localizationService.getMessage("needAuthorization"),"401")));
            } else {
                response.sendRedirect("/login");
            }
        }).accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.NAva, localizationService.getMessage("accessDenied"))));
        }).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


}





