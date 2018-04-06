package com.alphaz.infrastructure.domain.service.base

import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 *@Author: c0der
 *@Date: 上午10:56 2018/3/13
 *@Description:
 */
interface DomainService<T : BaseDO<T, ID>, ID, REPO : BaseRepository<T, ID>> {
    val log: Logger

    val l: LocalizationService

    var repository: REPO

    fun createOrUpdate(t: T): T;

    fun createOrUpdate(list: List<T>): MutableList<T>;

    fun createOrUpdateFlush(t: T): T;

    fun createOrUpdateFlush(list: List<T>): MutableList<T>;

    fun findById(id: ID): T?;

    fun findAll(specification: Specification<T>): MutableList<T>;

    fun getListByPage(specification: Specification<T>?, pageable: Pageable): Page<T>;

    fun delete(t: T);

    fun deleteById(t: ID);

    fun deleteBySpecification(specification: Specification<T>);

}