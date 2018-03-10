package com.alphaz.api.controller;


import com.alphaz.application.authorization.dto.user.UserModel;
import com.alphaz.application.authorization.dto.user.UserUpdateModel;
import com.alphaz.application.authorization.service.PrivilegeService;
import com.alphaz.application.authorization.service.UserService;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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