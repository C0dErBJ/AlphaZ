package com.alphaz.core.authorization.model.role;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午5:41
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class Role {
    public Long id;
    public String roleName;
    public String description;
    public boolean editable;

    public boolean getIseditable() {
        return editable;
    }

    public void setIseditable(boolean iseditable) {
        this.editable = iseditable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role(Long id, String rolename, String description, boolean editable) {
        this.id = id;
        this.roleName = rolename;
        this.description = description;
        this.editable = editable;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
