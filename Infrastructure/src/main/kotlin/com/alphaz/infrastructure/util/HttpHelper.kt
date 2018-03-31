package com.alphaz.infrastructure.util

import javax.servlet.ServletRequest

/**
 *@Author: c0der
 *@Date: 下午3:17 2018/3/31
 *@Description:
 */
class HttpHelper {
    companion object {
        fun getClientIP(request: ServletRequest): String? {
            var remoteAddress = request.getAttribute("X-FORWARDED-FOR") as String?;
            if (remoteAddress.isNullOrEmpty()) {
                remoteAddress = request.remoteAddr;
            }
            return remoteAddress;
        }
    }
}