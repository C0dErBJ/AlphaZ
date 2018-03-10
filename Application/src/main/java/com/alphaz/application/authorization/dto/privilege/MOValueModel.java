package com.alphaz.application.authorization.dto.privilege;


import com.alphaz.infrastructure.domain.constant.State;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.auth
 * User: C0dEr
 * Date: 2017/7/7
 * Time: 下午6:31
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.auth
 */
public class MOValueModel {
    public Long menuid;
    public Long operationid;
    public State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}
