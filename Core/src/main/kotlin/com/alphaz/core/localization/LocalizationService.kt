package com.alphaz.core.localization

import com.alphaz.core.shared.service.DomainServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.Resource

/**
 *@Author: c0der
 *@Date: 下午3:46 2018/1/8
 *@Description:
 */
@Service
open class LocalizationService {

    private val logger = LoggerFactory.getLogger(LocalizationService::class.java)!!

    @Resource
    private lateinit var messageSource: MessageSource

    open fun getMessage(code: String): String {
        return getMessage(code, null, LocaleContextHolder.getLocale())
    }

    open fun getMessage(code: String, locale: Locale): String {
        return getMessage(code, null, locale)
    }

    open fun getMessage(code: String, args: Array<String>): String {
        return getMessage(code, args, LocaleContextHolder.getLocale())
    }

    open fun getMessage(code: String, args: Array<String>?, locale: Locale): String {
        try {
            val message = messageSource.getMessage(code, args, locale);
            return message;
        } catch (ex: Exception) {
            logger.info("未在${LocaleContextHolder.getLocale()}找到${code}")
        }
        return "[$code]";
    }
}