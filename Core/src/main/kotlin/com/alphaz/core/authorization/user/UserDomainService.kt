package com.alphaz.core.authorization.user

import com.alphaz.core.shared.service.DomainServiceImpl
import com.alphaz.infrastructure.exception.BusinessErrorException
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *@Author: c0der
 *@Date: 下午4:44 2018/3/16
 *@Description:
 */
@Service
@Transactional
open class UserDomainService : DomainServiceImpl(), UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return object : UserDetails {
            override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isEnabled(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getUsername(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isCredentialsNonExpired(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getPassword(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isAccountNonExpired(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isAccountNonLocked(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            val password = null
            val username = null
            val isAccountNonExpired: Boolean? = null

            val isAccountNonLocked: Boolean? = null

            val isCredentialsNonExpired: Boolean? = null

            val isEnabled: Boolean? = null
        }
    }

    @Autowired
    private lateinit var userRepository: BaseRepository<User, Long>

    open fun print() {
        var us = User();
        us.version = 0
        var a = userRepository.save(us);
        println(a);
    }

    open fun changePassword(newPassword: String, user: User) {
        if (user.id == null) {
            throw BusinessErrorException(l.getMessage("UserIdNotNullable"));
        }
        var uu = this.userRepository.findById(user.id!!)

    }
}