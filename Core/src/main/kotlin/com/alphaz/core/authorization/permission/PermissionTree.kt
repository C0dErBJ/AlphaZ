package com.alphaz.core.authorization.permission

import com.alphaz.core.authorization.permission.operation.Operation
import com.alphaz.infrastructure.domain.ValueObject

/**
 *@Author: c0der
 *@Date: 下午1:27 2018/3/28
 *@Description:
 */
data class PermissionTree(val childPermissionTree: MutableSet<PermissionTree>,
                          val label: String,
                          val icon: String?,
                          val operation: MutableSet<Operation>) : ValueObject {

}