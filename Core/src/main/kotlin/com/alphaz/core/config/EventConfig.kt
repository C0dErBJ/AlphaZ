package com.alphaz.core.config

import com.alphaz.core.authorization.UserSignInEvent
import org.greenrobot.eventbus.EventBus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *@Author: c0der
 *@Date: 下午3:01 2018/3/25
 *@Description:
 */
@Configuration
open class EventConfig {
    @Autowired
    private lateinit var userSignInEvent: UserSignInEvent;

    @Bean
    open fun event(): EventBus {
        return EventBus.builder().build();
    }

    @Bean
    open fun eventRegister(@Autowired eventBus: EventBus): Boolean {
        eventBus.register(userSignInEvent);
        return true;
    }
}