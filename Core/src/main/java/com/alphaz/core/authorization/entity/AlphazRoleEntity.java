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
@Table(name = "alphaz_role", schema = "alphaz", catalog = "")
public class AlphazRoleEntity extends BaseEntity {
    private String roleName;
    private String label;
    private String permissionLevel;
    private String description;
    private Integer sort;
    private boolean isEditable;

    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    public boolean getEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        this.isEditable = editable;
    }


    @Basic
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }


}
