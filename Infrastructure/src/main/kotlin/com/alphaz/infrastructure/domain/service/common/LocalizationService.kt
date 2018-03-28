package com.alphaz.infrastructure.domain.service.common

import org.springframework.context.i18n.LocaleContextHolder
import java.util.*

/**
 *@Author: c0der
 *@Date: 下午3:01 2018/3/28
 *@Description:
 */
interface LocalizationService {
    fun getMessage(code: String): String;

    fun getMessage(code: String, locale: Locale): String;

    fun getMessage(code: String, args: Array<String>): String;

    fun getMessage(code: String, args: Array<String>?, locale: Locale): String;
}