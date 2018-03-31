package com.alphaz.core.authorization.auth2

import javax.persistence.*
import java.sql.Timestamp
import java.util.Objects

/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "oauth_approvals", schema = "", catalog = "")
class OauthApprovalsEntity {
    @get:Basic
    @get:Column(name = "userId")
    var userId: String? = null
    @get:Basic
    @get:Column(name = "clientId")
    var clientId: String? = null
    @get:Basic
    @get:Column(name = "scope")
    var scope: String? = null
    @get:Basic
    @get:Column(name = "status")
    var status: String? = null
    @get:Basic
    @get:Column(name = "expiresAt")
    var expiresAt: Timestamp? = null
    @get:Basic
    @get:Column(name = "lastModifiedAt")
    var lastModifiedAt: Timestamp? = null
    @get:javax.persistence.Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
