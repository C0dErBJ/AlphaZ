package com.alphaz.infrastructure.web.advice

import com.alphaz.infrastructure.constant.enums.Status
import com.alphaz.infrastructure.domain.model.common.ResponseModel
import com.alphaz.infrastructure.web.annotation.WrapResult
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

/**
 *@Author: c0der
 *@Date: 上午12:46 2018/4/7
 *@Description:
 */

@ControllerAdvice(basePackages = ["com.alphaz"])
open class ResponseWrapper : ResponseBodyAdvice<Any> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun beforeBodyWrite(body: Any, returnType: MethodParameter?, selectedContentType: MediaType?, selectedConverterType: Class<out HttpMessageConverter<*>>?, request: ServerHttpRequest?, response: ServerHttpResponse?): Any {
        if (selectedContentType != null && selectedContentType == MediaType.TEXT_HTML) {
            return body;
        }
        return ResponseModel<Any>(Status.SUCCESS, null, body, "200")
    }


    override fun supports(returnType: MethodParameter?, converterType: Class<out HttpMessageConverter<*>>?): Boolean {
        if (returnType != null && returnType.hasMethodAnnotation(WrapResult::class.java)) {
            val anno = returnType.getMethodAnnotation(WrapResult::class.java)
            return anno!!.isWrap
        }
        return true;
    }


}