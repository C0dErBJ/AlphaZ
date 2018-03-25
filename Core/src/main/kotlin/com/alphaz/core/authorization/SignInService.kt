package com.alphaz.core.authorization

import com.alphaz.core.authorization.user.*
import com.alphaz.core.shared.service.DomainServiceImpl
import com.alphaz.infrastructure.exception.BusinessErrorException
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

/**
 *@Author: c0der
 *@Date: 下午10:31 2018/3/20
 *@Description:
 */
@Service
@Transactional
@Validated
open class SignInService : DomainServiceImpl<User, Long, UserRepository>() {
    @Autowired
    private lateinit var userPolicy: UserPolicy;
    @Autowired
    private lateinit var eventBus: EventBus
    @Autowired
    private lateinit var userSignInEvent: UserSignInEvent

    open fun login(@NotNull username: String, @NotNull password: String): User {

        val user = this.repository.findFirstByUsernameAndPassword(username, userPolicy.encode(password))

        eventBus.post(user ?: User(username, null));

        val existUser = this.repository.findFirstByUsername(username);

        if (existUser != null) {
            existUser.loginFailCount++;
            this.repository.save(existUser);
        }
        if (user == null) {
            throw BusinessErrorException(l.getMessage("UsernameOrPasswordNotCorrect"))
        }

        userPolicy.userSignInValid(user);

        return user;
    }

}