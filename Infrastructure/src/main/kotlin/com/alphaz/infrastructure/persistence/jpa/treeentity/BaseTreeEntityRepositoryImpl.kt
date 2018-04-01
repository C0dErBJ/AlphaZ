package com.alphaz.infrastructure.persistence.jpa.treeentity

import com.alphaz.infrastructure.domain.model.treeentity.BaseTreeEntity
import com.alphaz.infrastructure.domain.model.treeentity.BaseTreeEntity_
import com.alphaz.infrastructure.persistence.jpa.BaseRepositoryImpl
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import javax.persistence.EntityManager

/**
 *@Author: c0der
 *@Date: 下午11:25 2018/3/27
 *@Description:
 */
@SuppressWarnings("unchecked")
open class BaseTreeEntityRepositoryImpl<C : BaseTreeEntity<C>>(jpaEntityInformation: JpaEntityInformation<C, Long>,
                                                               private var entityManager: EntityManager)
    : BaseTreeEntityRepository<C>, BaseRepositoryImpl<C, Long>(jpaEntityInformation, entityManager) {
    //todo Tree的操作
    fun getTreeByRootId(rootId: Long): List<C>? {
        val treeEntityList = this.findAll({ root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get(BaseTreeEntity_.root), rootId)
        });
        return treeEntityList;
    }

    fun getDescendants(nodeId: Long): List<C>? {
        val currentNode = this.findById(nodeId);
        if (!currentNode.isPresent) {
            return null;
        }
        return findAll({ root, _, criteriaBuilder ->
            criteriaBuilder.and(criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseTreeEntity_.root), currentNode.get().root),
                    criteriaBuilder.like(root.get(BaseTreeEntity_.code), currentNode.get().code + BaseTreeEntity.SEPERATOR + "%")),
                    criteriaBuilder.greaterThan(root.get(BaseTreeEntity_.layer), currentNode.get().layer));
        });
    }

    fun getSiblings(nodeId: Long): List<C>? {
        val currentNode = this.findById(nodeId);
        if (!currentNode.isPresent) {
            return null;
        }
        return findAll(Specification { root, _, criteriaBuilder ->
            criteriaBuilder.and(criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseTreeEntity_.layer), currentNode.get().layer),
                    criteriaBuilder.equal(root.get(BaseTreeEntity_.root), currentNode.get().root)),
                    criteriaBuilder.notEqual(root.get(BaseTreeEntity_.id), currentNode.get().id));
        })
    }
}