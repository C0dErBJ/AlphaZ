package com.alphaz.infrastructure.dao.jpa

import com.alphaz.infrastructure.domain.model.entity.BaseEntity
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 *@Author: c0der
 *@Date: 下午3:26 2018/3/8
 *@Description:
 */
@NoRepositoryBean
interface BaseRepository<T : BaseEntity, ID> : JpaRepository<T, ID> {

    fun JQF(): HibernateQueryFactory

    fun removeById(id: ID);

    fun remove(entity:T);

}