package com.alphaz.core.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

/**
 *@Author: c0der
 *@Date: 下午1:24 2018/3/20
 *@Description:
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class ResourceServerConfig : ResourceServerConfigurerAdapter() {
    @Value("\${resource.id:spring-boot-application}")
    private val resourceId: String? = null
    @Value("\${security.oauth2.resource.jwt.private-key}")
    private val privateKey: String? = null
    @Autowired
    private val tokenEnhancer: JwtAccessTokenConverter? = null

    override fun configure(config: ResourceServerSecurityConfigurer?) {
        config!!.tokenServices(tokenServices()).resourceId(resourceId).stateless(false)
    }

    @Bean
    open fun tokenStore(): TokenStore {
        return JwtTokenStore(tokenEnhancer)
    }


    @Bean
    @Primary
    open fun tokenServices(): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        return defaultTokenServices
    }

    @Throws(Exception::class)
    open override fun configure(http: HttpSecurity) {
        http.authorizeRequests().anyRequest().authenticated().antMatchers("/favicon.ico",
                "/system/**",
                "/error",
                "/image",
                "/resources/**",
                "/script/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**"
        ).permitAll()
    }
}