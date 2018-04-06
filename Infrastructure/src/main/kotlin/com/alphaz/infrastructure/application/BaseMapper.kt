package com.alphaz.infrastructure.application

import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.domain.model.base.BaseDO

/**
 *@Author: c0der
 *@Date: 下午4:15 2018/4/6
 *@Description:
 */
interface BaseMapper<DTO : BaseDto, T : BaseDO<*, *>> {
    fun toDo(dto: DTO): T
    fun toDos(dto: List<DTO>): List<T>
    fun toDto(t: T): DTO
    fun toDtos(ts: List<T>): List<DTO>
}