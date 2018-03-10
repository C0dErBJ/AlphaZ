package com.alphaz.core.authorization.entity;

import com.alphaz.infrastructure.domain.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:40
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_menu_operation", schema = "alphaz", catalog = "")
public class AlphazMenuOperationEntity extends BaseEntity {
    private Long menuid;
    private Long operationid;


    @Basic
    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    @Basic
    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }



}
