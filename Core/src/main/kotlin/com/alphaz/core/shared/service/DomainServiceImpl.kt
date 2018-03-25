package com.alphaz.core.shared.service

import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.BaseDO
import com.alphaz.infrastructure.domain.Entity
import com.alphaz.infrastructure.domain.filter.annotation.StateFilter
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 *@Author: c0der
 *@Date: 上午10:57 2018/3/13
 *@Description:
 */
@StateFilter
abstract class DomainServiceImpl<T : BaseDO<T, ID>, ID, REPO : BaseRepository<T, ID>> : DomainService<T, ID, REPO> {
    override val log = LoggerFactory.getLogger(this.javaClass)!!;
    @Autowired
    override lateinit var l: LocalizationService;
    @Autowired
    override lateinit var repository: REPO;

    override fun delete(t: T) {
        this.repository.remove(t);
    }

    override fun save(t: T) {
        this.repository.save(t)
    }

    override fun findAll(): MutableList<T> {
        return this.repository.findAll();
    }

    override fun findById(t: ID): T? {
        val entity = this.repository.findById(t);
        if (entity.isPresent) {
            return entity.get();
        }
        return null;
    }
}