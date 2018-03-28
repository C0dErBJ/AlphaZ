package com.alphaz.infrastructure.persistence.jpa.treeentity

import com.alphaz.infrastructure.domain.model.TreeEntity.BaseTreeEntity
import com.alphaz.infrastructure.persistence.jpa.BaseRepositoryImpl
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder

/**
 *@Author: c0der
 *@Date: 下午11:25 2018/3/27
 *@Description:
 */
open class BaseTreeEntityRepositoryImpl<C : BaseTreeEntity<*, *>, ID>(jpaEntityInformation: JpaEntityInformation<C, ID>,
                                                                      private var entityManager: EntityManager)
    : BaseTreeEntityRepository<C, ID>, BaseRepositoryImpl<C, ID>(jpaEntityInformation, entityManager) {
    //todo Tree的操作
    fun getTreeByRootId(rootId: ID): MutableSet<C>? {
//        var specification = Specification<C>();
//        var query = this.entityManager.criteriaBuilder.createQuery();
//        var root = query.from()
//        var builder = this.entityManager.criteriaBuilder;
//        var  root  =  criteriaQuery.from(specification.getType());
        return null;


    }
}