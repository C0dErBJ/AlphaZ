package com.alphaz.infrastructure.persistence.jpa

import com.alphaz.infrastructure.domain.BaseDO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 *@Author: c0der
 *@Date: 下午3:26 2018/3/8
 *@Description:
 */
@NoRepositoryBean
interface BaseRepository<T : BaseDO<*, *>, ID> : JpaRepository<T, ID> {

    fun removeById(id: ID);

    fun remove(entity: T);

}