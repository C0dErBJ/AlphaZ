package com.alphaz.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import java.util.*

/**
 *@Author: c0der
 *@Date: 下午1:25 2018/3/20
 *@Description:
 */
//@Configuration
//@EnableOAuth2Client
open class OauthClientConfig {
    @Bean
    open fun restTemplate(oauth2ClientContext: OAuth2ClientContext): OAuth2RestOperations {
        return OAuth2RestTemplate(resource(), oauth2ClientContext)
    }

    private fun resource(): OAuth2ProtectedResourceDetails {
        val resource = AuthorizationCodeResourceDetails()
        resource.clientId = ""
        resource.clientSecret = ""
        resource.accessTokenUri = ""
        resource.userAuthorizationUri = ""
        resource.scope = Arrays.asList("read")

        return resource
    }
}