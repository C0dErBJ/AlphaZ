package com.alphaz.infrastructure.application

import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.domain.service.base.DomainService
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 *@Author: c0der
 *@Date: 下午2:37 2018/4/1
 *@Description:
 */
interface BaseAppService<D : BaseDto<ID>, T : BaseDO<T, ID>, ID, out DS : DomainService<T, ID, *>, out Mapper:BaseMapper<D,T>> {
    val l: LocalizationService
    val domainService: DS
    val mapper: Mapper

    fun createOrUpdate(t: List<D>): MutableList<D>;
    fun createOrUpdate(t: D): D;
    fun getDetailById(id: ID): D?;
    fun getListByPage(specification: Specification<T>?, pageable: Pageable): Page<D>
    fun deleteById(id: ID);
}