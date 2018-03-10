package com.alphaz.core.authorization.model.permission;


import com.alphaz.infrastructure.domain.constant.common.State;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/7/7
 * Time: 下午5:10
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class Operation {
    public Long id;
    public String operationname;
    public String label;
    public String icon;
    public State isenabled;

    public State getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(State isenabled) {
        this.isenabled = isenabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
