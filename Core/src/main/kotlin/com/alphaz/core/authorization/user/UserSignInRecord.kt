package com.alphaz.core.authorization.user

import com.alphaz.infrastructure.domain.model.BaseDO
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 *@Author: c0der
 *@Date: 下午5:29 2018/3/25
 *@Description:
 */
@Entity
@Table(name = "alphaz_user_sign_record", catalog = "")
data class UserSignInRecord(
        @field:Size(max = 256)
        @field:NotNull
        var userId: Long?,

        @field:Size(max = 256)
        @field:NotNull
        var username: String?,

        @field:Size(max = 512)
        @field:NotNull
        var clientIp: String?,

        @field:NotNull
        @field:Size(max = 512)
        var loginTime: LocalDateTime?


) : BaseDO<User, Long>(), com.alphaz.infrastructure.domain.Entity {
    constructor() : this(null, null, null, null)

}