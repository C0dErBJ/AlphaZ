package com.alphaz.api.controller;


import com.alphaz.core.authorization.service.PrivilegeService;
import com.alphaz.core.authorization.service.UserService;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.user.LoginModel;
import com.alphaz.core.pojo.viewmodel.user.UserModel;
import com.alphaz.core.pojo.viewmodel.user.UserUpdateModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by C0dEr on 2016/12/3.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    UserService userService;
    @Resource
    PrivilegeService privilegeService;


    @GetMapping("/{username}/role")
    public ResponseModel getRoleByUser(@PathVariable String username) {
        return this.privilegeService.getUserRoleByUsername(username);
    }

    @GetMapping("/{id}/menu/op")
    public ResponseModel getMOByUser(@PathVariable Long id) {
        return this.privilegeService.getMOByUserid(id);
    }

    @PostMapping("/new")
    public ResponseModel addUser(@Valid UserModel model) {
        return userService.addUser(model);
    }

    @PostMapping("/forgetpassword")
    public ResponseModel forgetPassword(String username, String password) {
        return userService.changePassword(username, password);
    }

    @PatchMapping("/{userid}")
    public ResponseModel updateUser(@PathVariable Long userid, @Valid UserUpdateModel model) {
        return userService.updateUser(userid, model);
    }

    @DeleteMapping("/{userid}")
    public ResponseModel deleteUser(@PathVariable Long userid) {
        return this.userService.deleteUser(userid);
    }

//    @GetMapping("/search")
//    public DataTableModel findUser(@Valid UserSearchModel model) {
//        return this.userService.findUser(model);
//    }
}