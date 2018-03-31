package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*
import java.util.Arrays
import java.util.Objects

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_access_token", schema = "", catalog = "")
class OauthAccessTokenEntity {
    @get:Basic
    @get:Column(name = "token_id")
    var tokenId: String? = null
    @get:Basic
    @get:Column(name = "token")
    var token: ByteArray? = null
    @get:Id
    @get:Column(name = "authentication_id")
    var authenticationId: String? = null
    @get:Basic
    @get:Column(name = "user_name")
    var userName: String? = null
    @get:Basic
    @get:Column(name = "client_id")
    var clientId: String? = null
    @get:Basic
    @get:Column(name = "authentication")
    var authentication: ByteArray? = null
    @get:Basic
    @get:Column(name = "refresh_token")
    var refreshToken: String? = null

}
