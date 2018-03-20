package com.alphaz.core.shared.service

import com.alphaz.core.localization.LocalizationService
import org.slf4j.Logger

/**
 *@Author: c0der
 *@Date: 上午10:56 2018/3/13
 *@Description:
 */
interface DomainService {
    val log: Logger
    var l: LocalizationService;
}