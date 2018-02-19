package com.alphaz.core.pojo.dto

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod

/**
 *@Author: c0der
 *@Date: 下午6:40 2018/1/9
 *@Description:
 */
data class DynamicApiDto(val name: String,
                         val serviceName: String,
                         val method: Set<String>,
                         val contentType: Set<MediaType>,
                         val httpMethod: Set<RequestMethod>)