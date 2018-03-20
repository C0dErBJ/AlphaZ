package com.alphaz.infrastructure.domain

import com.alphaz.infrastructure.constant.State
import com.alphaz.infrastructure.exception.NotAValueObjectException
import com.alphaz.infrastructure.exception.NotAnEntityException
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient
import javax.persistence.PrePersist


/**
 *@Author: c0der
 *@Date: 下午4:30 2018/3/15
 *@Description:An entity, as explained in the DDD，对于entity的独有操作需要在entity中实现
 */
@MappedSuperclass
@FilterDef(name = "dataStateCondition", parameters = arrayOf(ParamDef(name = "state", type = "int")))
@Filter(name = "dataStateCondition", condition = "state = :state")
abstract class BaseDO<C : DO<*, *>, T> : DO<C, T> {
    override fun equal(other: C): Boolean {
        return this.id == other.id;
    }

    /**
     * 主键，UUID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: T? = null

    /**
     * 逻辑删除
     */
    @Column(name = "state", nullable = true, columnDefinition = "tinyint default 1", length = 1)
    override var state: State = State.ACTIVE
    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    override var createTime: LocalDateTime = LocalDateTime.now()
    /**
     * 更新时间
     */
    @Column(name = "update_time", columnDefinition = "datetime on update CURRENT_TIMESTAMP")
    override var updateTime: LocalDateTime? = null

    /**
     * 创建人
     */
    override var createBy: T? = null

    /**
     * 更新人
     */
    override var updateBy: T? = null

    override var owner: T? = null

    @Column(name = "version", nullable = false, columnDefinition = "int default 1", length = 1)
    override var version: Int = 0

    @Version
    @Transient
    var lock: Int? = null


}