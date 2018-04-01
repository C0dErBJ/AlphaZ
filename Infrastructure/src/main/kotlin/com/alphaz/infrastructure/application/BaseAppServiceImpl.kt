package com.alphaz.infrastructure.application

import com.alphaz.infrastructure.domain.service.base.DomainService
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *@Author: c0der
 *@Date: 下午2:38 2018/4/1
 *@Description:
 */
@Service
open class BaseAppServiceImpl : BaseAppService {
    @Autowired
    override lateinit var l: LocalizationService;
}