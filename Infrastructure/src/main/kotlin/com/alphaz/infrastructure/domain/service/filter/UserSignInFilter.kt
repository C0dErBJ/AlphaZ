package com.alphaz.infrastructure.domain.service.filter

import javax.servlet.*

/**
 *@Author: c0der
 *@Date: 下午10:01 2018/3/26
 *@Description:
 */
open class UserSignInFilter:Filter{
    override fun destroy() {
        println("ccc");
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("bbb");
    }

    override fun init(filterConfig: FilterConfig?) {
        println("aaa");
    }

}