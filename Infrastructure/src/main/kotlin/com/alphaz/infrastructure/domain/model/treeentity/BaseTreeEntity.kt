package com.alphaz.infrastructure.domain.model.treeentity

import com.alphaz.infrastructure.domain.model.BaseDO
import com.alphaz.infrastructure.domain.DO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 下午11:11 2018/3/27
 *@Description:
 */
@MappedSuperclass
abstract class BaseTreeEntity<C : DO<C, Long>> : BaseDO<C, Long>() {

    companion object {
        val SEPERATOR = '-';
    }

    var root: Long? = null;
    var parentId: Long? = null;
    var code: String? = null;
    var layer: Int = 0;
}