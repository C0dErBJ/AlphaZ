package com.alphaz.core.authorization.user

import com.alphaz.core.authorization.permission.Permission
import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.service.DomainServiceImpl
import com.alphaz.infrastructure.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 *@Author: c0der
 *@Date: 下午4:44 2018/3/16
 *@Description:
 */
@Service
@Transactional
@Validated
open class UserService : DomainServiceImpl<User, Long, UserRepository>(), UserDetailsService {
    @Autowired
    private lateinit var userPolicy: UserPolicy

    override fun loadUserByUsername(username: String?): UserDetails? {
        assert(username != null);
        return repository.findFirstByUsername(username!!)?.let<User?, UserDetails> { it ->
            object : UserDetails {
                override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
                    return null;
                }

                override fun isEnabled(): Boolean {
                    return it!!.isEnabled
                }

                override fun getUsername(): String {
                    return it?.username!!
                }

                override fun isCredentialsNonExpired(): Boolean {
                    return it?.isCredentialsNonExpired!!
                }

                override fun getPassword(): String {
                    return it?.password!!
                }

                override fun isAccountNonExpired(): Boolean {
                    return it?.isAccountNonExpired!!
                }

                override fun isAccountNonLocked(): Boolean {
                    return it?.isAccountNonLocked!!
                }

            }
        };
    }

    open fun changePassword(newPassword: String, user: User) {
        if (user.id == null) {
            throw BusinessErrorException(l.getMessage("UserIdNotNullable"));
        }
        val currentUser = this.repository.findById(user.id!!)
        if (!currentUser.isPresent) {
            throw BusinessErrorException(l.getMessage("UserNotFound"));
        }
        currentUser.get().password = userPolicy.encode(newPassword);
        this.repository.save(currentUser.get());
    }

    open fun unlockUser(user: User) {
        user.unlock();
        this.repository.save(user);
    }

    @Transactional
    open fun userPermissions(user: User): MutableSet<Permission>? {
        val permissions = mutableSetOf<Permission>();
        user.roles?.forEach { a ->
            apply {
                if (a.permissions != null) {
                    permissions.addAll(a.permissions!!)
                }
            }
        }
        return permissions.distinctBy { a -> a.id }.toMutableSet();
    }
}