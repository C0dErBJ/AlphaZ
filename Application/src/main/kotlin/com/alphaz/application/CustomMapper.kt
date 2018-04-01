package com.alphaz.application

import com.alphaz.application.authorization.user.dto.UserDto
import com.alphaz.core.authorization.user.User
import org.mapstruct.Mapper

/**
 *@Author: c0der
 *@Date: 下午3:27 2018/4/1
 *@Description:
 */
@Mapper
interface CustomMapper {
    fun toUserDto(user: User): UserDto

    fun toUserDtos(users: List<User>): List<UserDto>
}
