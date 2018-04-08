package com.alphaz.api.controller

import com.alphaz.application.authorization.user.UserAppService
import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.core.authorization.user.User
import com.alphaz.infrastructure.web.base.BaseControllerImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@Author: c0der
 *@Date: 下午10:14 2018/4/1
 *@Description:
 */
@RestController
@RequestMapping("user")
@PreAuthorize("permitAll()")
open class UserController : BaseControllerImpl<UserDto, User, Long, UserAppService>() {
    @PostMapping("kkk")
    open fun a(uuu: Boolean): Boolean {
        return false;
    }
}