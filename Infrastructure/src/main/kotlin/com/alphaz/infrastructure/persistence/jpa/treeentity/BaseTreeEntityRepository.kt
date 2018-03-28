package com.alphaz.infrastructure.persistence.jpa.treeentity

import com.alphaz.infrastructure.domain.model.TreeEntity.BaseTreeEntity
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 *@Author: c0der
 *@Date: 下午11:21 2018/3/27
 *@Description:
 */
@NoRepositoryBean
interface BaseTreeEntityRepository<C : BaseTreeEntity<*, *>, ID> : BaseRepository<C, ID>