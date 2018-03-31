package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_code", schema = "", catalog = "")
class OauthCodeEntity {
    @get:Basic
    @get:Column(name = "code")
    var code: String? = null
    @get:Basic
    @get:Column(name = "authentication")
    var authentication: ByteArray? = null
    @get:javax.persistence.Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
