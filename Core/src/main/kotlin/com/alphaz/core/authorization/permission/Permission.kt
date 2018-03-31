package com.alphaz.core.authorization.permission

import com.alphaz.core.authorization.permission.operation.Operation
import com.alphaz.core.authorization.role.Role
import com.alphaz.infrastructure.domain.model.treeentity.BaseTreeEntity
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
                      var icon: String?,
                      @OneToMany(targetEntity = Operation::class, mappedBy = "permission")
                      var operations: MutableSet<Operation>?,
                      @ManyToMany(targetEntity = Role::class, mappedBy = "permissions")
                      var roles: MutableSet<Role>?) : BaseTreeEntity<Permission>(), com.alphaz.infrastructure.domain.Entity {

    constructor() : this(null, null, null, null, null)

    fun addOperation(operation: Operation) {
        if (this.operations == null) {
            this.operations = mutableSetOf(operation)
        } else {
            this.operations!!.add(operation)
        }
    }

    fun addOperations(operations: MutableSet<Operation>) {
        if (this.operations == null) {
            this.operations = operations;
        } else {
            this.operations!!.addAll(operations);
        }
    }

    fun removeOperation(operation: Operation) {
        this.operations?.removeIf { a -> a.sameAs(operation) }
    }

    fun removeAllOperaions() {
        this.operations = null;
    }

}