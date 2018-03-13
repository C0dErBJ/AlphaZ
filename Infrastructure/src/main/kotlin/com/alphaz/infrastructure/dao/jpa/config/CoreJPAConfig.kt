package com.alphaz.infrastructure.dao.jpa.config

import com.alphaz.infrastructure.dao.jpa.BaseRepositoryImpl
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 *@Author: c0der
 *@Date: 上午9:57 2018/3/13
 *@Description:
 */
@Configuration
@EnableJpaRepositories(value = "com.alphaz", repositoryBaseClass = BaseRepositoryImpl::class)
@EntityScan(basePackages = ["com.alphaz.core"])
open class CoreJPAConfig {
}