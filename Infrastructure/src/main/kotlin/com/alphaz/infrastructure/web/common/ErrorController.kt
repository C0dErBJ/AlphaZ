package com.alphaz.infrastructure.web.common

import com.alphaz.infrastructure.constant.enums.Status
import com.alphaz.infrastructure.domain.model.common.ErrorInfo
import com.alphaz.infrastructure.domain.model.common.ResponseModel
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

/**
 *@Author: c0der
 *@Date: 下午10:36 2018/4/1
 *@Description:
 */
@Controller
@RequestMapping("\${server.error.path:\${error.path:/error}}")
open class ErrorController : ErrorController {
    companion object {
        const val PATH = "/error"

    }

    @RequestMapping(value = PATH, produces = ["text/html"])
    open fun errorHtml(request: HttpServletRequest): ModelAndView {
        val errors = DefaultErrorAttributes().getErrorAttributes(request as WebRequest, true)
        val mav: ModelAndView
        mav = when (errors.get("status").toString()) {
            "404" -> ModelAndView("system/404")
            "403" -> ModelAndView("system/403")
            "500" -> ModelAndView("system/500")
            else -> ModelAndView("system/500")
        }
        val message = errors["message"];
        mav.addObject("message", message ?: "")
        return mav
    }

    @ResponseBody
    @RequestMapping(value = PATH)
    open fun error(request: HttpServletRequest): HttpEntity<ResponseModel<*>> {
        val errors = DefaultErrorAttributes().getErrorAttributes(request as WebRequest, true)
        val model = ResponseModel(Status.FAILED,
                ErrorInfo(errors["message"].toString(), errors["errors"]),
                null, errors["status"].toString())
        return ResponseEntity(model, HttpStatus.valueOf(Integer.valueOf(model.httpStatus)))
    }

    override fun getErrorPath(): String {
        return PATH
    }
}