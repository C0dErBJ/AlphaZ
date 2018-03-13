package com.alphaz.core.authorization.entity;

import com.alphaz.infrastructure.domain.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:39
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_rmo", catalog = "")
public class AlphazRMOEntity extends BaseEntity {
    private Long rid;
    private Long moid;



    @Basic
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Basic
    public Long getMoid() {
        return moid;
    }

    public void setMoid(Long moid) {
        this.moid = moid;
    }




}
