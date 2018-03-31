package com.alphaz.infrastructure.persistence.jpa.treeentity

import com.alphaz.infrastructure.domain.model.treeentity.BaseTreeEntity
import com.alphaz.infrastructure.domain.model.treeentity.BaseTreeEntity_
import com.alphaz.infrastructure.persistence.jpa.BaseRepositoryImpl
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import javax.persistence.EntityManager

/**
 *@Author: c0der
 *@Date: 下午11:25 2018/3/27
 *@Description:
 */
open class BaseTreeEntityRepositoryImpl<C : BaseTreeEntity<C>>(jpaEntityInformation: JpaEntityInformation<C, Long>,
                                                               private var entityManager: EntityManager)
    : BaseTreeEntityRepository<C>, BaseRepositoryImpl<C, Long>(jpaEntityInformation, entityManager) {
    //todo Tree的操作
    fun getTreeByRootId(rootId: Long): List<C>? {
        val treeEntityList = this.findAll({ root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get(BaseTreeEntity_.root), rootId)
        });
        return treeEntityList;
    }
}