package com.alphaz.infrastructure.dao.jpa

import com.alphaz.infrastructure.domain.constant.common.State
import com.alphaz.infrastructure.domain.model.entity.BaseEntity
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.Session
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.repository.NoRepositoryBean
import javax.persistence.EntityManager

/**
 *@Author: c0der
 *@Date: 下午3:58 2018/3/8
 *@Description:
 */
@NoRepositoryBean
open class BaseRepositoryImpl<T : BaseEntity, ID>(jpaEntityInformation: JpaEntityInformation<T, ID>, private var entityManager: EntityManager)
    : SimpleJpaRepository<T, ID>(jpaEntityInformation, entityManager), BaseRepository<T, ID> {
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


    override fun JQF(): HibernateQueryFactory {
        return HibernateQueryFactory(entityManager.unwrap(Session::class.java))
    }


}