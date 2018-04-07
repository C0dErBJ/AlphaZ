package com.alphaz.infrastructure.web.common

import com.alphaz.infrastructure.constant.enums.Status
import com.alphaz.infrastructure.domain.model.common.ErrorInfo
import com.alphaz.infrastructure.domain.model.common.ResponseModel
import com.alphaz.infrastructure.domain.service.common.LocalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ErrorProperties
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *@Author: c0der
 *@Date: 下午10:36 2018/4/1
 *@Description:
 */
@Controller
@RequestMapping("\${server.error.path:\${error.path:/error}}")
open class WebErrorController(errorAttributes: ErrorAttributes,
                              errorViewResolvers: List<ErrorViewResolver>) : AbstractErrorController(errorAttributes, errorViewResolvers) {


    private val errorProperties: ErrorProperties = ErrorProperties()

    @Autowired
    private lateinit var l: LocalizationService;

    init {
        errorProperties.isIncludeException = true;
        errorProperties.includeStacktrace = ErrorProperties.IncludeStacktrace.ALWAYS
    }

    override fun getErrorPath(): String {
        return this.errorProperties.path;
    }

    @RequestMapping(value = [this.errorProperties.path], produces = ["text/html"])
    fun errorHtml(request: HttpServletRequest?, response: HttpServletResponse?): ModelAndView {
        val errors = DefaultErrorAttributes().getErrorAttributes(request as WebRequest, true)
        val mav: ModelAndView
        //todo 设置错误页面
        mav = when (errors["status"].toString()) {
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
    @RequestMapping(value = [this.errorProperties.path])
    fun error(request: HttpServletRequest): ResponseEntity<ResponseModel<Any>>? {
        val isIncludeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
        val body = getErrorAttributes(request,
                isIncludeStackTrace)
        val status = getStatus(request)
        val message = body["message"] as String?;
        val error = body["error"] as String;
        var trace = ""
        if (isIncludeStackTrace) {
            trace = body["trace"] as String;
        }
        return ResponseEntity(ResponseModel(Status.FAILED, ErrorInfo(error, message, trace, null), Status.FAILED, httpStatus = status.toString()), status)
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @param produces the media type produced (or `MediaType.ALL`)
     * @return if the stacktrace attribute should be included
     */
    private fun isIncludeStackTrace(request: HttpServletRequest,
                                    produces: MediaType): Boolean {
        val include = errorProperties.includeStacktrace
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true
        }
        return if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            getTraceParameter(request)
        } else false
    }

}