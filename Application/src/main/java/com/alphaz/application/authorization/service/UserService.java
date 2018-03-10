package com.alphaz.application.authorization.service;

import com.alphaz.application.authorization.dto.user.UserModel;
import com.alphaz.application.authorization.dto.user.UserUpdateModel;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
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

    String findEmailByUsername(String username);

    ResponseModel changePassword(String username, String password);
}
