package com.alphaz.infrastructure.application

import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.domain.service.base.DomainServiceImpl
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 *@Author: c0der
 *@Date: 下午2:38 2018/4/1
 *@Description:
 */
open class BaseAppServiceImpl<D : BaseDto<ID>, T : BaseDO<T, ID>, ID, DomainService : DomainServiceImpl<T, ID, *>, Mapper : BaseMapper<D, T>> : BaseAppService<D, T, ID, DomainService, Mapper> {
    @Autowired
    override lateinit var l: LocalizationService;
    @Autowired
    override lateinit var domainService: DomainService;
    @Autowired
    override lateinit var mapper: Mapper;


    override fun createOrUpdate(t: List<D>): MutableList<D> {
        val result = domainService.createOrUpdate(mapper.toDos(t));
        return mapper.toDtos(result).toMutableList();
    }

    override fun createOrUpdate(t: D): D {
        val result = domainService.createOrUpdate(mapper.toDo(t));
        return mapper.toDto(result);
    }

    override fun getDetailById(id: ID): D? {
        val result = domainService.findById(id) ?: return null;
        return mapper.toDto(result)
    }

    override fun getListByPage(specification: Specification<T>?, pageable: Pageable): Page<D> {
        val result = domainService.getListByPage(specification, pageable);
        return PageImpl<D>(mapper.toDtos(result.content), pageable, result.totalElements);
    }

    override fun deleteById(id: ID) {
        return domainService.deleteById(id)
    }


}