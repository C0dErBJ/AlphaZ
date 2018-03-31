package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_client_details", schema = "", catalog = "")
class OauthClientDetailsEntity {
    @get:Id
    @get:Column(name = "client_id")
    var clientId: String? = null
    @get:Basic
    @get:Column(name = "resource_ids")
    var resourceIds: String? = null
    @get:Basic
    @get:Column(name = "client_secret")
    var clientSecret: String? = null
    @get:Basic
    @get:Column(name = "scope")
    var scope: String? = null
    @get:Basic
    @get:Column(name = "authorized_grant_types", columnDefinition = "varchar(70) default 'client_credentials,password,authorization_code,refresh_token'")
    var authorizedGrantTypes: String? = null
    @get:Basic
    @get:Column(name = "web_server_redirect_uri")
    var webServerRedirectUri: String? = null
    @get:Basic
    @get:Column(name = "authorities")
    var authorities: String? = null
    @get:Basic
    @get:Column(name = "access_token_validity")
    var accessTokenValidity: Int? = null
    @get:Basic
    @get:Column(name = "refresh_token_validity")
    var refreshTokenValidity: Int? = null
    @get:Basic
    @get:Column(name = "additional_information")
    var additionalInformation: String? = null
    @get:Basic
    @get:Column(name = "autoapprove")
    var autoapprove: String? = null

}
