package com.alphaz.core.authorization.user

import com.alphaz.infrastructure.domain.model.base.BaseDO
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
        var userId: Long?,

        @field:Size(max = 256)
        @field:NotNull
        var username: String?,

        @field:Size(max = 512)
        var clientIp: String?,

        @field:NotNull
        var loginTime: LocalDateTime?,

        val isSuccess: Boolean = true


) : BaseDO<UserSignInRecord, Long>(), com.alphaz.infrastructure.domain.Entity {
    constructor() : this(null, null, null, null)

}