package com.alphaz.api.controller;


import com.alphaz.core.authorization.service.PrivilegeService;
import com.alphaz.core.authorization.service.UserService;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.user.LoginModel;
import com.alphaz.core.pojo.viewmodel.user.UserModel;
import com.alphaz.core.pojo.viewmodel.user.UserUpdateModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseModel login(LoginModel model, HttpServletResponse response) {
//        if (model.getRemember()) {
//            String namepassword = model.getUsername().concat("|%|#|").concat(model.getPassword());
//            String namepassword64 = null;
//            try {
//                namepassword64 = Base64.getEncoder().encodeToString(namepassword.getBytes("utf-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            Cookie cookie = new Cookie("a&p", namepassword64);
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 60 * 24 * 30);
//
//            response.addCookie(cookie);
//        }
//        if (!model.getRemember()) {
//            Cookie cookie = new Cookie("a&p", null);
//            cookie.setPath("/");
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//        }
        ResponseModel<UserViewModel> resultModel = userService.login(model.getUsername(), model.getPassword());
        if (resultModel.state == DataState.Ava) {

//            UserToken token = new UserToken(resultModel.data.getUsername(), resultModel.data.getPassword());
//            SecurityUtils.getSubject().login(token);
//            SecurityUtils.getSubject().getSession().setAttribute(SessionConstant.CURRENTUSER, resultModel.data);
        }
        return resultModel;
    }


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