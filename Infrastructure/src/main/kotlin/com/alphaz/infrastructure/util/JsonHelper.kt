package com.alphaz.infrastructure.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 *@Author: c0der
 *@Date: 下午9:40 2018/3/25
 *@Description:
 */
class JsonHelper {
    companion object {
        private val mapper = jacksonObjectMapper();

        fun toString(t: Any): String {
            return mapper.writeValueAsString(t)
        }
    }


}