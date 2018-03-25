package com.alphaz.core.authorization.permission

import com.alphaz.core.authorization.permission.operation.Operation
import com.alphaz.core.authorization.role.Role
import com.alphaz.infrastructure.domain.BaseDO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 下午5:04 2018/3/15
 *@Description:
 */
@Entity
@Table(name = "alphaz_permission", catalog = "")
data class Permission(var permissionName: String?,
                      var label: String?,
                      var parentid: Long?,
                      @GeneratedValue(strategy = GenerationType.AUTO)
                      var sort: Int,
                      var icon: String?,
                      @OneToMany(targetEntity = Operation::class, mappedBy = "permission")
                      var operations: MutableSet<Operation>?,

                      @ManyToMany(targetEntity = Role::class, mappedBy = "permissions")
                      var roles: MutableSet<Role>?) : BaseDO<Permission, Long>(),com.alphaz.infrastructure.domain.Entity {

    constructor() : this(null, null, null, 0, null, null, null)
}