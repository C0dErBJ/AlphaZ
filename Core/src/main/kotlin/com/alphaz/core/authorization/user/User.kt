package com.alphaz.core.authorization.user

import com.alphaz.core.authorization.role.Role
import com.alphaz.infrastructure.domain.BaseDO
import javax.persistence.*

/**
 *@Author: c0der
 *@Date: 下午5:04 2018/3/15
 *@Description:
 */
@Entity
@Table(name = "alphaz_user", catalog = "")
data class User(var username: String?,
                var password: String?,
                var name: String?,
                var email: String?,
                @Column(columnDefinition = "tinyint default 1", length = 1)
                var isEnabled: Boolean,
                @Column(columnDefinition = "tinyint default 1", length = 1)
                var isAccountNonExpired: Boolean,
                @Column(columnDefinition = "tinyint default 1", length = 1)
                var isAccountNonLocked: Boolean,
                @Column(columnDefinition = "tinyint default 1", length = 1)
                var isCredentialsNonExpired: Boolean,
                var resetCode: String?,
                @Column(columnDefinition = "tinyint default 1", length = 1)
                var isTwoFactorEnabled: Boolean,
                @ManyToMany(targetEntity = Role::class)
                @JoinTable(name = "alphaz_user_role",
                        joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
                        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
                var roles: MutableSet<Role>?
) : BaseDO<User, Long>() {
    constructor() : this(null, null, null, null, true, true, true, true, null, true, null)
}