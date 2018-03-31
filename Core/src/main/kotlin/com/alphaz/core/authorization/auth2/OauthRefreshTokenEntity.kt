package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_refresh_token", schema = "", catalog = "")
class OauthRefreshTokenEntity {
    @get:Basic
    @get:Column(name = "token_id")
    var tokenId: String? = null
    @get:Basic
    @get:Column(name = "token")
    var token: ByteArray? = null
    @get:Basic
    @get:Column(name = "authentication")
    var authentication: ByteArray? = null
    @get:javax.persistence.Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


}
