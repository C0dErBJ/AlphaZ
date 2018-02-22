package com.alphaz.core.authorization.config;

import com.alphaz.core.authorization.service.UserService;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.LocalizationService;
import com.alphaz.util.string.JsonHelper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: c0der
 * @Date: 下午3:45 2018/1/24
 * @Description:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public void configure(WebSecurity web) {
        //此处配置静态资源文件过滤
        web.ignoring().antMatchers("/favicon.ico",
                "/system/**",
                "/error",
                "/image",
                "/resources/**",
                "/script/**"
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
                    response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.NAva, localizationService.getMessage("loginSuccess"))));
                })
                .failureHandler((request, response, authentication) -> {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().write(JsonHelper.toString(new ResponseModel<>(DataState.Ava, localizationService.getMessage("loginFail"))));
                        }
                ).and()
                //请求中带remember-me参数
//                .rememberMe().rememberMeCookieDomain("alphaz.com").rememberMeCookieName("rememberme.alphaz").tokenRepository().and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).and()
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


    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}





