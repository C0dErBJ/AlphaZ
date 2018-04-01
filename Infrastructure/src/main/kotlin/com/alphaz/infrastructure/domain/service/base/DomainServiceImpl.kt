package com.alphaz.infrastructure.domain.service.base

import com.alphaz.infrastructure.domain.filter.annotation.DataState
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 *@Author: c0der
 *@Date: 上午10:57 2018/3/13
 *@Description:
 */
@DataState
abstract class DomainServiceImpl<T : BaseDO<T, ID>, ID, REPO : BaseRepository<T, ID>> : DomainService<T, ID, REPO> {
    override val log = LoggerFactory.getLogger(this.javaClass)!!;
    @Autowired
    override lateinit var repository: REPO;
    @Autowired
    override lateinit var l: LocalizationService;

    override fun delete(t: T) {
        this.repository.remove(t);
    }

    override fun saveOrUpdate(t: T) {
        this.repository.save(t)
    }

   override fun getPageList(specification: Specification<T>?, pageable: Pageable): Page<T> {
        return this.repository.getPageList(specification, pageable)
    }
}