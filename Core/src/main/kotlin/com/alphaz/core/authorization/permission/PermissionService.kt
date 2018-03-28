package com.alphaz.core.authorization.permission

import com.alphaz.infrastructure.domain.service.DomainServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 *@Author: c0der
 *@Date: 下午1:55 2018/3/28
 *@Description:
 */
@Service
@Transactional
@Validated
open class PermissionService : DomainServiceImpl<Permission, Long, PermissionRepository>() {
    fun getPermissionTree() {

    }
}