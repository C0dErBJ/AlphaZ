package com.alphaz.api.controller

import com.alphaz.application.authorization.user.UserAppService
import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.core.authorization.user.User
import com.alphaz.infrastructure.web.BaseController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@Author: c0der
 *@Date: 下午10:14 2018/4/1
 *@Description:
 */
@RestController
@RequestMapping("user")
open class UserController : BaseController<UserDto, User, Long, UserAppService>() {

}