package com.alphaz.api.handler;

import com.alphaz.infrastructure.domain.constant.State;
import com.alphaz.infrastructure.domain.model.common.ErrorInfo;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * User: C0dEr
 * Date: 2017/6/8
 * Time: 下午7:15
 * Description:This is a class of com.alphaz.controller
 */
@Controller
public class GlobalErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, produces = "text/html")
    public ModelAndView error_html(HttpServletRequest request) {
        Map<String, Object> errors = new DefaultErrorAttributes().getErrorAttributes((WebRequest) new ServletRequestAttributes(request), true);
        ModelAndView mav;
        switch (errors.get("status").toString()) {
            case "404":
                mav = new ModelAndView("system/404");
                break;
            case "403":
                mav = new ModelAndView("system/403");
                break;
            case "500":
                mav = new ModelAndView("system/500");
                break;
            default:
                mav = new ModelAndView("system/500");
                break;
        }
        mav.addObject("message", errors.get("message"));
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = PATH)
    public HttpEntity<ResponseModel> error(HttpServletRequest request) {
        Map<String, Object> errors = new DefaultErrorAttributes().getErrorAttributes((WebRequest) new ServletRequestAttributes(request), true);
        ResponseModel<Object> model = new ResponseModel<>(State.NO,
                new ErrorInfo(errors.get("message").toString(), errors.get("errors")),
                null, errors.get("status").toString());
        return new ResponseEntity<>(model, HttpStatus.valueOf(Integer.valueOf(model.getHttpStatus())));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}