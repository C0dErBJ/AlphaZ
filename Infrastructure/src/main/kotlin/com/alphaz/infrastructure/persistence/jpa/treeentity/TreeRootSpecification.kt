package com.alphaz.infrastructure.persistence.jpa.treeentity

import com.alphaz.infrastructure.domain.model.TreeEntity.BaseTreeEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 *@Author: c0der
 *@Date: 下午2:15 2018/3/28
 *@Description:
 */
class TreeRootSpecification : Specification<BaseTreeEntity<*, *>> {
    override fun toPredicate(root: Root<BaseTreeEntity<*, *>>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
      return  null
    }
}