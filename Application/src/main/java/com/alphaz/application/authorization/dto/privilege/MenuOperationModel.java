package com.alphaz.application.authorization.dto.privilege;

import com.alphaz.core.authorization.model.permission.Menu;
import com.alphaz.core.authorization.model.permission.Operation;

import java.util.List;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 下午3:06
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class MenuOperationModel {
    public Map<String, List<String>> namePair;
    public Map<Menu, List<Operation>> keyPair;

    public Map<String, List<String>> getNamePair() {
        return namePair;
    }

    public void setNamePair(Map<String, List<String>> namePair) {
        this.namePair = namePair;
    }

    public Map<Menu, List<Operation>> getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(Map<Menu, List<Operation>> keyPair) {
        this.keyPair = keyPair;
    }
}
