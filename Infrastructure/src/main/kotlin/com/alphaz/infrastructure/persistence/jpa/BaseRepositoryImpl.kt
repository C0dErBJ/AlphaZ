package com.alphaz.infrastructure.persistence.jpa

import com.alphaz.infrastructure.constant.State
import com.alphaz.infrastructure.domain.BaseDO
import com.querydsl.jpa.hibernate.HibernateQueryFactory
import org.hibernate.Session
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

/**
 *@Author: c0der
 *@Date: 下午3:58 2018/3/8
 *@Description:
 */
@NoRepositoryBean
open class BaseRepositoryImpl<T : BaseDO<*, *>, ID>(jpaEntityInformation: JpaEntityInformation<T, ID>, private var entityManager: EntityManager)
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

    fun a() {
    }


}