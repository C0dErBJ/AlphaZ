package com.alphaz.core.authorization.permission.operation

import com.alphaz.core.authorization.permission.Permission
import com.alphaz.infrastructure.domain.model.base.BaseDO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 上午10:57 2018/3/16
 *@Description:
 */
@Entity
@Table(name = "alphaz_operation", catalog = "")
data class Operation(
        var operationName: String?,
        var label: String?,
        @OneToOne(targetEntity = Permission::class)
        @JoinColumn(name = "permission_id")
        var permission: Permission?,
        var icon: String?
) : BaseDO<Operation, Long>(), com.alphaz.infrastructure.domain.Entity {
    constructor() : this(null, null, null, null)

}