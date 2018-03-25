package com.alphaz.core.shared.service

import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.BaseDO
import com.alphaz.infrastructure.domain.Entity
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import com.sun.mail.imap.protocol.ID
import org.slf4j.Logger

/**
 *@Author: c0der
 *@Date: 上午10:56 2018/3/13
 *@Description:
 */
interface DomainService<T : BaseDO<T, ID>, ID, REPO : BaseRepository<T, ID>> {
    val log: Logger
    var l: LocalizationService;
    var repository: REPO

    fun save(t: T);

    fun delete(t: T);

    fun findAll(): MutableList<T>;

    fun findById(t: ID): T?;
}