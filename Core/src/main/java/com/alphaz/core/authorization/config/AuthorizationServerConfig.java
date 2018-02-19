package com.alphaz.core.authorization.config;

import com.alphaz.core.authorization.service.UserService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: c0der
 * @Date: 上午10:26 2018/2/6
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;

    @Value("${security.oauth2.resource.jwt.private-key}")
    private String privateKey;
    @Value("${security.oauth2.resource.jwt.public-key}")
    private String publicKey;

    @Value("${resource.id:spring-boot-application}") // 默认值spring-boot-application
    private String resourceId;

    @Value("${access_token.validity_period:3600}") // 默认值3600
            int accessTokenValiditySeconds = 3600;
    @Autowired
    private DataSource dataSource;

    @Value("${tonr.redirect:http://localhost:8080/dashboard}")
    private String redirectUri;


    @Autowired
    private AuthenticationManager authenticationManager;
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) {
        configurer.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("isAnonymous() || hasRole('ROLE_TRUSTED_CLIENT')") // permitAll()
                .checkTokenAccess("hasRole('TRUSTED_CLIENT')"); // isAuthenticated()
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //todo 持久化到数据库
//        clients.jdbc(dataSource);
        clients.inMemory()

                // Confidential client where client secret can be kept safe (e.g. server side)
                .withClient("confidential").secret("secret")
                .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
                .scopes("read", "write")
                .redirectUris("http://localhost:8080/client/")

                .and()

                // Public client where client secret is vulnerable (e.g. mobile apps, browsers)
                .withClient("public") // No secret!
                .authorizedGrantTypes("implicit")
                .scopes("read")
                .redirectUris("http://localhost:8080/client/")

                .and()

                // Trusted client: similar to confidential client but also allowed to handle user password
                .withClient("trusted").secret("secret")
                .authorities("ROLE_TRUSTED_CLIENT")
                .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
                .scopes("read", "write")
                .redirectUris("http://localhost:8080/client/")
        ;
    }




}

