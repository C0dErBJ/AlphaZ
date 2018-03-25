package com.alphaz.core.authorization

import com.alphaz.core.authorization.user.User
import com.alphaz.core.authorization.user.UserSignInRecord
import com.alphaz.core.authorization.user.UserRepository
import com.alphaz.infrastructure.domain.DomainEvent
import com.alphaz.infrastructure.persistence.jpa.BaseRepository
import org.greenrobot.eventbus.Subscribe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 *@Author: c0der
 *@Date: 下午5:22 2018/3/25
 *@Description:
 */
@Component
@Transactional
open class UserSignInEvent : DomainEvent<User> {
    @Autowired
    private lateinit var userRepository: UserRepository;
    @Autowired
    private lateinit var userSignInRecordRepository: BaseRepository<UserSignInRecord, Long>;
    @Subscribe
    fun loginRecord(user: User) {
        val loginRecord = UserSignInRecord(user.id, user.username, null, LocalDateTime.now());
        userSignInRecordRepository.save(loginRecord);
    }
}