package com.alphaz.application.authorization.user.dto

import com.alphaz.infrastructure.application.dto.BaseDto
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 *@Author: c0der
 *@Date: 下午2:57 2018/4/1
 *@Description:
 */
@ApiModel
data class UserDto(@field:Size(max = 256)
                   @field:NotNull
                   var username: String?,
                   @field:NotNull
                   @field:Size(max = 256)
                   var name: String?,
                   @field:Size(max = 512)
                   var email: String?,
                   @ApiModelProperty
                   var isEnabled: Boolean?,
                   var isAccountNonExpired: Boolean?,
                   var isAccountNonLocked: Boolean?,
                   var isCredentialsNonExpired: Boolean?,
                   var resetCode: String?,
                   var isTwoFactorEnabled: Boolean?
) : BaseDto<Long>() {
    constructor() : this(null, null,
            null, null,
            null, null, null, null, null)

}