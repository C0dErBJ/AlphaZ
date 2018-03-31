package com.alphaz.core.authorization.user

import com.alphaz.core.authorization.permission.Permission
import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.service.DomainServiceImpl
import com.alphaz.infrastructure.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
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
        val matchedUser = repository.findFirstByUsername(username!!) ?: throw UsernameNotFoundException("UserNotFound")
        return org.springframework.security.core.userdetails.User(matchedUser.username,
                matchedUser.password, matchedUser.isEnabled,
                matchedUser.isAccountNonExpired,
                matchedUser.isCredentialsNonExpired,
                matchedUser.isAccountNonLocked,
                mutableListOf())
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