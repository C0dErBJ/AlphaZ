package com.alphaz.infrastructure.domain.service

import com.alphaz.infrastructure.domain.model.BaseDO
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.slf4j.Logger

/**
 *@Author: c0der
 *@Date: 上午10:56 2018/3/13
 *@Description:
 */
interface DomainService<T : BaseDO<T, ID>, ID, REPO : BaseRepository<T, ID>> {
    val log: Logger

    val l: LocalizationService

    var repository: REPO

    fun save(t: T);

    fun delete(t: T);

    fun findAll(): MutableList<T>;

    fun findById(t: ID): T?;
}