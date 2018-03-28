package com.alphaz.infrastructure.domain.model.TreeEntity

import com.alphaz.infrastructure.domain.model.BaseDO
import com.alphaz.infrastructure.domain.DO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 下午11:11 2018/3/27
 *@Description:
 */
@MappedSuperclass
abstract class BaseTreeEntity<C : DO<*, *>, ID> : BaseDO<C, ID>() {
    var root: ID? = null;
    var parentId: ID? = null;
    var code: String? = null;
    var layer: Int = 0;
}