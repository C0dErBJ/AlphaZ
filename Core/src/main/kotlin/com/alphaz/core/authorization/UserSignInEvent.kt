package com.alphaz.core.authorization

import com.alphaz.core.authorization.user.UserSignInRecord
import com.alphaz.core.authorization.user.UserSignInRecordRepository
import com.alphaz.infrastructure.domain.DomainEvent
import org.greenrobot.eventbus.Subscribe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 *@Author: c0der
 *@Date: 下午5:22 2018/3/25
 *@Description:
 */
@Component
@Transactional
open class UserSignInEvent : DomainEvent {
    @Autowired
    private lateinit var userSignInRecordRepository: UserSignInRecordRepository;

    @Subscribe
    open fun loginRecord(userSignInRecord: UserSignInRecord) {
        userSignInRecordRepository.save(userSignInRecord);
    }
}