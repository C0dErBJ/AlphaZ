package com.alphaz.application.authorization.service;

import com.alphaz.application.authorization.dto.privilege.AllRoleMenuVIewModel;
import com.alphaz.application.authorization.dto.privilege.MOValueModel;
import com.alphaz.application.authorization.dto.privilege.MenuOperationModel;
import com.alphaz.application.authorization.dto.user.UserViewModel;
import com.alphaz.core.authorization.model.role.Role;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
import com.alphaz.infrastructure.domain.service.common.BaseService;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午10:53
 * Description:This is a class of com.alphaz.core.service
 */
public interface PrivilegeService extends BaseService {
    UserViewModel getUserRole(String username);

    ResponseModel getUserRoleByUsername(String username);

    MenuOperationModel getMenuOperationByUserid(Long userid);

    ResponseModel getMOByUserid(Long userid);

    ResponseModel getMenuByUserId(Long userid);

    ResponseModel getRoles(Long userid);

    ResponseModel deleteRole(Long roleid);

    ResponseModel<AllRoleMenuVIewModel> getAllRoleAndMenuOperation();

    MenuOperationModel getMenuOperationByRoleid(Long roleid);

    ResponseModel getMOByRoleid(Long roleid);

    ResponseModel addRole(String rolename, String description);

    ResponseModel updateRole(Role role);

    ResponseModel getRoleById(Long roleid);

    ResponseModel updateRMObymoid(Long roleid, List<MOValueModel> map);
}
