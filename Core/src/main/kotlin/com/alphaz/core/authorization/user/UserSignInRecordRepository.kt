package com.alphaz.core.authorization.user

import com.alphaz.infrastructure.persistence.jpa.BaseRepository

/**
 *@Author: c0der
 *@Date: 下午3:07 2018/3/28
 *@Description:
 */
interface UserSignInRecordRepository : BaseRepository<UserSignInRecord, Long> {
}