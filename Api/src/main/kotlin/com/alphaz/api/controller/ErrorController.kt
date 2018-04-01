package com.alphaz.api.controller

/**
 *@Author: c0der
 *@Date: 下午10:36 2018/4/1
 *@Description:
 */
class ErrorController{
    private val PATH = "/error"

//    @RequestMapping(value = PATH, produces = "text/html")
//    fun error_html(request: HttpServletRequest): ModelAndView {
//        val errors = DefaultErrorAttributes().getErrorAttributes(request as WebRequest, true)
//        val mav: ModelAndView
//        when (errors.get("status").toString()) {
//            "404" -> mav = ModelAndView("system/404")
//            "403" -> mav = ModelAndView("system/403")
//            "500" -> mav = ModelAndView("system/500")
//            else -> mav = ModelAndView("system/500")
//        }
//        mav.addObject("message", errors.get("message"))
//        return mav
//    }
//
//    @ResponseBody
//    @RequestMapping(value = PATH)
//    fun error(request: HttpServletRequest): HttpEntity<ResponseModel> {
//        val errors = DefaultErrorAttributes().getErrorAttributes(request as WebRequest, true)
//        val model = ResponseModel(Status.FAILED,
//                ErrorInfo(errors.get("message").toString(), errors.get("errors")),
//                null, errors.get("status").toString())
//        return ResponseEntity(model, HttpStatus.valueOf(Integer.valueOf(model.getHttpStatus())))
//    }
//
//    fun getErrorPath(): String {
//        return PATH
//    }
}