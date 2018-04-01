package com.alphaz.infrastructure.persistence.jpa

import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 *@Author: c0der
 *@Date: 下午3:26 2018/3/8
 *@Description:
 */
@NoRepositoryBean
interface BaseRepository<T : BaseDO<T, ID>, ID> : JpaRepository<T, ID> {
    val jqf: HibernateQueryFactory;

    fun removeById(id: ID);

    fun remove(entity: T);

    fun getPageList(specification: Specification<T>?, pageable: Pageable): Page<T>

}