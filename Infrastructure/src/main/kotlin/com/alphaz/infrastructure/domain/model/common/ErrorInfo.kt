package com.alphaz.infrastructure.domain.model.common

/**
 *@Author: c0der
 *@Date: 上午11:18 2018/3/10
 *@Description:
 */
data class ErrorInfo(val message: String, val stacktrace: String?, val validationError: Any?) {
    constructor(message: String) : this(message, null, null);
    constructor(message: String, stacktrace: String?) : this(message, stacktrace, null);
    constructor(message: String, validationError: Any?) : this(message, null, validationError);
}