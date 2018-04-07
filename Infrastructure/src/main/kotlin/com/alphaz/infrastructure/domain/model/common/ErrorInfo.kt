package com.alphaz.infrastructure.domain.model.common

/**
 *@Author: c0der
 *@Date: 上午11:18 2018/3/10
 *@Description:
 */
data class ErrorInfo(val error: String, val message: String?, val stacktrace: String?, val validationError: Any?) {

    constructor(error: String) : this(error, null, null, null);
    constructor(error: String, message: String?) : this(error, message, null, null)
}