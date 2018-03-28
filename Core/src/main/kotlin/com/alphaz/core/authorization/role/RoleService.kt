package com.alphaz.core.authorization.role

import com.alphaz.infrastructure.domain.service.DomainServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 *@Author: c0der
 *@Date: 下午4:56 2018/3/27
 *@Description:
 */
@Service
@Transactional
@Validated
open class RoleService : DomainServiceImpl<Role, Long, RoleRepository>() {

}