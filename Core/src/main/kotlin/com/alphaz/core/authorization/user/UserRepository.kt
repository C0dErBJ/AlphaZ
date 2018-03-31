package com.alphaz.core.authorization.user

import com.alphaz.infrastructure.persistence.jpa.BaseRepository

/**
 *@Author: c0der
 *@Date: 上午10:09 2018/3/16
 *@Description:
 */
interface UserRepository : BaseRepository<User, Long> {

    fun findFirstByUsernameAndPassword(username: String, password: String): User?;
    fun findFirstByUsername(username: String): User?
}