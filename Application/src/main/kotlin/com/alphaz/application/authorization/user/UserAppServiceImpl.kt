package com.alphaz.application.authorization.user

import com.alphaz.application.CustomMapper
import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.core.authorization.user.UserService
import com.alphaz.infrastructure.application.BaseAppServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 *@Author: c0der
 *@Date: 下午2:48 2018/4/1
 *@Description:
 */
@Service
open class UserAppServiceImpl : UserAppService, BaseAppServiceImpl() {
    @Autowired
    private lateinit var mapper: CustomMapper;
    @Autowired
    private  lateinit var userService: UserService


    override fun getListByPage(pageable: Pageable): Page<UserDto> {
        val result = this.userService.getPageList(null, pageable)
        return PageImpl(mapper.toUserDtos(result.content), pageable, result.totalElements);
    }


}