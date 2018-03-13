package com.alphaz.api.controller;

import com.alphaz.application.authorization.dto.user.UserViewModel;
import com.alphaz.application.authorization.service.PrivilegeService;
import com.alphaz.application.authorization.service.UserService;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * @author C0dEr
 * @date 2016/12/3
 */
@Controller
public class IndexController {

    @Resource
    private PrivilegeService privilegeService;
    @Resource
    private UserService userService;

    @GetMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("a&p")) {
                    String deap = new String(Base64.getDecoder().decode(c.getValue()));
                    String[] ap = deap.split("\\|%\\|#\\|");
                    mav.addObject("account", ap.length >= 1 ? ap[0] : "");
                    mav.addObject("password", ap.length >= 2 ? ap[1] : "");
                    mav.addObject("rmb", ap.length >= 2);
                    break;
                }
            }
        }
        return mav;
    }

    @GetMapping("sidebarmenu")
    @ResponseBody
    public ResponseModel sidebarmenu(UserViewModel userViewModel) {
        return privilegeService.getMenuByUserId(userViewModel.getUserid());
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        return "redirect:login";
    }

    @GetMapping("dashboard")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("dashboard");
        return mav;
    }

    @GetMapping("privilege")
    public ModelAndView privilege() {
        ModelAndView mav = new ModelAndView("privilege/privilege");
        String entity = userService.findEmailByUsername("aaa");
        System.out.println(entity);
        return mav;
    }

    @GetMapping(value = "customerlist")
    public ModelAndView customerList() {
        ModelAndView mav = new ModelAndView("customer/customerlist");
        return mav;
    }

    @GetMapping(value = "userlist")
    public ModelAndView userList() {
        ModelAndView mav = new ModelAndView("user/userlist");
        return mav;
    }

    @GetMapping("productlist")
    public ModelAndView productlist() {
        ModelAndView mav = new ModelAndView("product/productlist");
        return mav;
    }

}
