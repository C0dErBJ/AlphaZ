package com.alphaz.infrastructure.dao.jpa.config;

import com.alphaz.infrastructure.dao.jpa.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.config
 * User: C0dEr
 * Date: 2017/7/20
 * Time: 下午4:13
 * Description:This is a class of com.alphaz.core.config
 */
@Configuration
@EnableJpaRepositories(value = "com.alphaz", repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackages = "com.alphaz.core")
public class CoreJPAConfig {


}
