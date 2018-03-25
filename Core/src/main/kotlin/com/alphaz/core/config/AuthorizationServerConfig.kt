package com.alphaz.core.config

import com.alphaz.core.authorization.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
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
    @Autowired
    private val userService: UserService? = null

    @Value("\${security.oauth2.resource.jwt.private-key}")
    private val privateKey: String? = null
    @Value("\${security.oauth2.resource.jwt.public-key}")
    private val publicKey: String? = null

    @Value("\${resource.id:spring-boot-application}")
    private val resourceId: String? = null

    @Autowired
    private val dataSource: DataSource? = null

    @Autowired
    private val userApprovalHandler: UserApprovalHandler? = null

    @Autowired
    private val clientDetailsService: ClientDetailsService? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Bean
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
        converter.setSigningKey(privateKey!!)
        converter.setVerifierKey(publicKey)
        return converter
    }

    @Bean
    open fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(tokenEnhancer())
    }

    open override fun configure(configurer: AuthorizationServerEndpointsConfigurer?) {
        configurer!!.tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer())
                .userDetailsService(userService).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
    }

    open override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        oauthServer!!.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients() // isAuthenticated()
    }

    @Throws(Exception::class)
    open override fun configure(clients: ClientDetailsServiceConfigurer?) {
        //todo 持久化到数据库
        clients!!.jdbc(dataSource).passwordEncoder(passwordEncoder)

    }
}