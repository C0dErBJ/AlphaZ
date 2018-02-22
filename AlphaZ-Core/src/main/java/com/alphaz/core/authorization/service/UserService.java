package com.alphaz.core.authorization.service;

import com.alphaz.core.pojo.viewmodel.DataTableModel;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.user.UserModel;
import com.alphaz.core.pojo.viewmodel.user.UserSearchModel;
import com.alphaz.core.pojo.viewmodel.user.UserUpdateModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:57
 * Description:
 */
public interface UserService extends UserDetailsService {

    ResponseModel addUser(UserModel model);

    ResponseModel deleteUser(Long userid);

    ResponseModel updateUser(Long userid, UserUpdateModel model);

    DataTableModel findUser(UserSearchModel model);

    String findEmailByUsername(String username);

    ResponseModel changePassword(String username, String password);
}
