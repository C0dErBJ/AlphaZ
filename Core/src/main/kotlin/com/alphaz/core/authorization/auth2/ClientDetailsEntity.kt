package com.alphaz.core.authorization.auth2

import java.util.*
import javax.persistence.*
/**
 * @Author: c0der
 * @Date: 下午1:26 2018/2/22
 * @Description:
 */
@Entity
@Table(name = "clientdetails", schema = "", catalog = "")
class ClientDetailsEntity {
    @get:Id
    @get:Column(name = "appId")
    var appId: String? = null
    @get:Basic
    @get:Column(name = "resourceIds")
    var resourceIds: String? = null
    @get:Basic
    @get:Column(name = "appSecret")
    var appSecret: String? = null
    @get:Basic
    @get:Column(name = "scope")
    var scope: String? = null
    @get:Basic
    @get:Column(name = "grantTypes")
    var grantTypes: String? = null
    @get:Basic
    @get:Column(name = "redirectUrl")
    var redirectUrl: String? = null
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
    @get:Column(name = "additionalInformation")
    var additionalInformation: String? = null
    @get:Basic
    @get:Column(name = "autoApproveScopes")
    var autoApproveScopes: String? = null
}
