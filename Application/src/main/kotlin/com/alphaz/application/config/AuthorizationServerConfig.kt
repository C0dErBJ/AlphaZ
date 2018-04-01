package com.alphaz.application.config

import com.alphaz.core.authorization.user.UserPolicy
import com.alphaz.core.authorization.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import javax.sql.DataSource

/**
 *@Author: c0der
 *@Date: 下午1:19 2018/3/20
 *@Description:
 */
@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {


    @Value("\${security.oauth2.resource.jwt.private-key}")
    private lateinit var privateKey: String
    @Value("\${security.oauth2.resource.jwt.public-key}")
    private lateinit var publicKey: String

    @Value("\${resource.id:spring-boot-application}")
    private lateinit var resourceId: String

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var dataSource: DataSource

    @Autowired
    private lateinit var clientDetailsService: ClientDetailsService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userPolicy: UserPolicy

    @Autowired
    open fun userApprovalHandler(tokenStore: TokenStore): TokenStoreUserApprovalHandler {
        val handler = TokenStoreUserApprovalHandler()
        handler.setTokenStore(tokenStore)
        handler.setRequestFactory(DefaultOAuth2RequestFactory(clientDetailsService))
        handler.setClientDetailsService(clientDetailsService)
        return handler
    }

    @Bean
    open fun tokenEnhancer(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey(privateKey)
        converter.setVerifierKey(publicKey)
        return converter
    }

    @Bean
    open fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(tokenEnhancer())
    }

    override fun configure(configurer: AuthorizationServerEndpointsConfigurer) {
        configurer.tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer())
                .userDetailsService(userService).userApprovalHandler(userApprovalHandler(tokenStore()))
                .authenticationManager(authenticationManager)
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients() // isAuthenticated()
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        //todo 持久化到数据库
        clients.jdbc(dataSource).passwordEncoder(userPolicy.passwordEncoder)

    }


}