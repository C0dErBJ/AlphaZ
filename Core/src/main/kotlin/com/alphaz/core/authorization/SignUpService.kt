package com.alphaz.core.authorization

import com.alphaz.core.authorization.user.User
import com.alphaz.core.authorization.user.UserPolicy
import com.alphaz.core.authorization.user.UserRepository
import com.alphaz.infrastructure.domain.service.DomainServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 *@Author: c0der
 *@Date: 下午10:33 2018/3/20
 *@Description:
 */
@Service
@Transactional
@Validated
open class SignUpService : DomainServiceImpl<User, Long, UserRepository>() {
    @Autowired
    private lateinit var userPolicy: UserPolicy;

    open fun register(user: User): User {
        userPolicy.userSignInValid(user);
        return this.repository.save(user);
    }
}