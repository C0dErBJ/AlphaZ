package com.alphaz.core.authorization.role

import com.alphaz.core.authorization.permission.Permission
import com.alphaz.core.authorization.user.User
import com.alphaz.infrastructure.domain.BaseDO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 下午5:04 2018/3/15
 *@Description:
 */
@Entity
@Table(name = "alphaz_role", catalog = "")
data class Role(var roleName: String?,
                var label: String?,
                var permissionLevel: String?,
                var description: String?,
                @GeneratedValue(strategy = GenerationType.AUTO)
                var sort: Int,
                var isEditable: Boolean,
                @ManyToMany(targetEntity = User::class)
                var user: MutableSet<User>?,

                @ManyToMany(targetEntity = Permission::class)
                @JoinTable(name = "alphaz_role_permission",
                        joinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")),
                        inverseJoinColumns = arrayOf(JoinColumn(name = "permission_id", referencedColumnName = "id")))
                var permissions: MutableSet<Permission>?
) : BaseDO<Role, Long>() {
    constructor() : this(null, null, null, null, 0, true, null, null)

}