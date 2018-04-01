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

    fun saveOrUpdate(t: T);

    fun delete(t: T);

    fun getPageList(specification: Specification<T>?, pageable: Pageable): Page<T>;
}