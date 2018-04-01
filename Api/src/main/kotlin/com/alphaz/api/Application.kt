package com.alphaz.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableAsync

/**
 *@Author: c0der
 *@Date: 下午10:33 2018/4/1
 *@Description:
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@ComponentScan(basePackages = ["com.alphaz"])
open class Application{
     fun main(args: Array<String>) {
        SpringApplication.run(Application::class.java, *args)
    }
}