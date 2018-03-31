package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_client_token", schema = "", catalog = "")
class OauthClientTokenEntity {
    @get:Id
    @get:Column(name = "authentication_id")
    var authenticationId: String? = null
    @get:Basic
    @get:Column(name = "token_id")
    var tokenId: String? = null
    @get:Basic
    @get:Column(name = "token")
    var token: ByteArray? = null
    @get:Basic
    @get:Column(name = "user_name")
    var userName: String? = null
    @get:Basic
    @get:Column(name = "client_id")
    var clientId: String? = null

}
