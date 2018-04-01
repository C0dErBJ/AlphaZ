package com.alphaz.infrastructure.application

import com.alphaz.infrastructure.domain.service.base.DomainService
import com.alphaz.infrastructure.domain.service.common.LocalizationService

/**
 *@Author: c0der
 *@Date: 下午2:37 2018/4/1
 *@Description:
 */
interface BaseAppService {
    val l: LocalizationService
}