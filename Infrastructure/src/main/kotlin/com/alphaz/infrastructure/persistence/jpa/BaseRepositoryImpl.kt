package com.alphaz.infrastructure.persistence.jpa

import com.alphaz.infrastructure.constant.enums.State
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.Session
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

/**
 *@Author: c0der
 *@Date: 下午3:58 2018/3/8
 *@Description:
 */
open class BaseRepositoryImpl<T : BaseDO<T, ID>, ID>(jpaEntityInformation: JpaEntityInformation<T, ID>, private var entityManager: EntityManager)
    : SimpleJpaRepository<T, ID>(jpaEntityInformation, entityManager), BaseRepository<T, ID> {

    open override val jqf: HibernateQueryFactory
        get() = HibernateQueryFactory(entityManager.unwrap(Session::class.java))

    /**
     * 逻辑删除
     */
    override fun removeById(id: ID) {
        var entity = this.findById(id);
        if (entity.isPresent) {
            entity.get().state = State.DELETED;
            super.save(entity.get());
        }
    }

    /**
     * 逻辑删除
     */
    override fun remove(entity: T) {
        entity.state = State.DELETED;
        super.save(entity);
    }

    /**
     * 分页查询
     */
    override fun getPageList(specification: Specification<T>?, pageable: Pageable): Page<T> {
        if (specification == null) {
            return super.findAll(pageable)
        }
        val result =
                super.getQuery(specification, pageable).resultList;
        val count = super.count(specification)
        return PageImpl<T>(result, pageable, count)

    }


}