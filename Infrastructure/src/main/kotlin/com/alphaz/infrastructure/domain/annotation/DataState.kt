package com.alphaz.infrastructure.domain.annotation

import java.lang.annotation.Inherited


/**
 *@Author: c0der
 *@Date: 下午5:40 2018/3/10
 *@Description:取消state过滤注解
 */


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class DataState(val enable: Boolean = true)





