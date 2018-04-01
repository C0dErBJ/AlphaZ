package com.alphaz.infrastructure.domain

import com.alphaz.infrastructure.constant.enums.State
import java.time.LocalDateTime

/**
 *@Author: c0der
 *@Date: 下午4:26 2018/3/15
 *@Description:本接口为对于entity，valueobject的抽象实现
 */
interface DO<C, ID> {
    /**
     * entity在ddd中用唯一标识区别
     */
    fun sameAs(other: C): Boolean

    /**
     * 目前主键泛型只能是nullable，kotlin不支持new T()
     */
    var id: ID?
    /**
     * 逻辑删除
     */
    var state: State
    /**
     * 创建时间
     */
    var createdAt: LocalDateTime
    /**
     * 更新时间
     */
    var updateAt: LocalDateTime?
    /**
     * 创建人
     */
    var createdBy: ID?
    /**
     * 更新人
     */
    var updatedBy: ID?

    var owner: ID?

    var version: Int

    var sort: Int
}