package com.alphaz.core.shared.service

import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.filter.annotation.StateFilter
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *@Author: c0der
 *@Date: 上午10:57 2018/3/13
 *@Description:
 */
@StateFilter
abstract class DomainServiceImpl : DomainService {
    override val log = LoggerFactory.getLogger(this.javaClass)!!;
    @Autowired
    override lateinit var l: LocalizationService;
}