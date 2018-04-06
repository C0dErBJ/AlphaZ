package com.alphaz.application.config

import com.alphaz.application.authorization.user.dto.UserMapper
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *@Author: c0der
 *@Date: 下午6:04 2018/4/1
 *@Description:
 */
@Configuration
open class ApplicationConfig {
    @Bean
    open fun userMapper(): UserMapper {
        return Mappers.getMapper(UserMapper::class.java)
    }

}