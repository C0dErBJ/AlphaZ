package com.alphaz.infrastructure.web.annotation

import java.lang.annotation.Inherited

/**
 *@Author: c0der
 *@Date: 上午12:46 2018/4/7
 *@Description:
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class WrapResult(val isWrap: Boolean = true)