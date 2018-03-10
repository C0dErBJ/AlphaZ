package com.alphaz.application.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import java.util.Arrays;

/**
 * @Author: c0der
 * @Date: 上午11:25 2018/2/6
 * @Description:
 */
//@Configuration
//@EnableOAuth2Client
public class OauthClientConfig {


    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resource(), oauth2ClientContext);
    }

    private OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId("");
        resource.setClientSecret("");
        resource.setAccessTokenUri("");
        resource.setUserAuthorizationUri("");
        resource.setScope(Arrays.asList("read"));

        return resource;
    }
}
