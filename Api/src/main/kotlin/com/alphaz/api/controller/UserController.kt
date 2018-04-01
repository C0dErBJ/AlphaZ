package com.alphaz.api.controller

import com.alphaz.application.authorization.user.UserAppService
import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.infrastructure.constant.AppConst
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@Author: c0der
 *@Date: 下午10:14 2018/4/1
 *@Description:
 */
@RestController
public class UserController {
    @Autowired
    private lateinit var userAppService: UserAppService

    @GetMapping
    fun user(@PageableDefault(sort = [(AppConst.defaltSort)]) pageable: Pageable): Page<UserDto> {
        return this.userAppService.getListByPage(pageable)
    }
}