package com.alphaz.core.authorization.dao;

import com.alphaz.core.authorization.entity.AlphazRMOEntity;
import com.alphaz.infrastructure.dao.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/10
 * Time: 下午6:11
 * Description:This is a class of com.alphaz.core.dao
 */
public interface RMORepository extends BaseRepository<AlphazRMOEntity, Long> {

    @Modifying
    @Transactional
    void deleteAlphazRMOEntitiesByRid(Long rid);
}
