package com.alphaz.core.authorization.user

import com.alphaz.core.authorization.role.Role
import com.alphaz.infrastructure.domain.model.BaseDO
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 *@Author: c0der
 *@Date: 下午5:04 2018/3/15
 *@Description:
 */
@Entity
@Table(name = "alphaz_user", catalog = "")
data class User(
        @field:Size(max = 256)
        @field:NotNull
        var username: String?,

        @field:Size(max = 512)
        @field:NotNull
        var password: String?,

        @field:NotNull
        @field:Size(max = 256)
        var name: String?,


        @field:Size(max = 512)
        var email: String?,
        var lastLoginTime: LocalDateTime?,

        var loginFailCount: Int = 0,

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
) : BaseDO<User, Long>(), com.alphaz.infrastructure.domain.Entity {
    constructor() : this(null, null, null, null, null, 0, true, true, true, true, null, true, null)
    constructor(username: String?, password: String?) : this(username, password, null, null, null, 0, true, true, true, true, null, true, null)

    fun unlock() {
        this.isAccountNonLocked = true;
        this.loginFailCount = 0;
    }

    fun addRole(role: Role) {
        if (this.roles == null) {
            this.roles = mutableSetOf(role);
        } else {
            this.roles!!.add(role)
        }
    }

    fun addRoles(roles: MutableSet<Role>) {
        if (this.roles == null) {
            this.roles = roles;
        } else {
            this.roles!!.addAll(roles)
        }
    }

    fun removeRole(role: Role) {
        this.roles?.removeIf { a -> a.sameAs(role) }
    }

    fun removeAllRoles() {
        this.roles = null;
    }
}