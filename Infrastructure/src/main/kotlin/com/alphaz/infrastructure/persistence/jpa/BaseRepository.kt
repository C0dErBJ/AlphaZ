package com.alphaz.infrastructure.persistence.jpa

import com.alphaz.infrastructure.domain.BaseDO
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.transaction.annotation.Transactional

/**
 *@Author: c0der
 *@Date: 下午3:26 2018/3/8
 *@Description:
 */
interface BaseRepository<T : BaseDO<*, *>, ID> : JpaRepository<T, ID> {
    val jqf: HibernateQueryFactory;

    fun removeById(id: ID);

    fun remove(entity: T);

}