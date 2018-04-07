package com.alphaz.application.authorization.user

import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.application.authorization.user.dto.UserMapper
import com.alphaz.core.authorization.user.User
import com.alphaz.core.authorization.user.UserService
import com.alphaz.infrastructure.application.BaseAppServiceImpl
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 *@Author: c0der
 *@Date: 下午2:48 2018/4/1
 *@Description:
 */
@Service
@Transactional
open class UserAppServiceImpl : UserAppService, BaseAppServiceImpl<UserDto, User, Long, UserService, UserMapper>() {

}