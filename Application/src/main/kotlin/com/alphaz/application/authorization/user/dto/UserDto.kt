package com.alphaz.application.authorization.user.dto

import com.alphaz.core.authorization.role.Role
import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 *@Author: c0der
 *@Date: 下午2:57 2018/4/1
 *@Description:
 */
data class UserDto(@field:Size(max = 256)
                   @field:NotNull
                   var username: String?,
                   @field:Size(max = 512)
                   @field:NotNull
                   var password: String?,
                   @field:NotNull
                   @field:Size(max = 256)
                   var name: String?,
                   @field:Size(max = 512)
                   var email: String?,
                   var lastLoginTime: LocalDateTime?,
                   var loginFailCount: Int = 0,
                   var isEnabled: Boolean,
                   var isAccountNonExpired: Boolean,
                   var isAccountNonLocked: Boolean,
                   var isCredentialsNonExpired: Boolean,
                   var resetCode: String?,
                   var isTwoFactorEnabled: Boolean,
                   var roles: MutableSet<Role>?) {
    constructor() : this(null, null, null,
            null, null, 0, true,
            true, true, true, null,true,null)

}