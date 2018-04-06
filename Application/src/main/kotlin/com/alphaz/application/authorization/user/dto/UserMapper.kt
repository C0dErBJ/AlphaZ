package com.alphaz.application.authorization.user.dto

import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.core.authorization.user.User
import com.alphaz.infrastructure.application.BaseMapper
import org.mapstruct.Mapper

/**
 *@Author: c0der
 *@Date: 下午3:27 2018/4/1
 *@Description:
 */
@Mapper
interface UserMapper : BaseMapper<UserDto, User> {

}
