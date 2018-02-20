package com.alphaz.core.service.impl

import com.alphaz.core.constant.ScriptType
import com.alphaz.core.pojo.dto.DynamicApiDto
import com.alphaz.core.service.ApiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.transaction.Transactional

/**
 *@Author: c0der
 *@Date: 下午6:37 2018/1/9
 *@Description:
 */
@Service
@Transactional
open class ApiServiceImpl : ApiService {

    @Autowired
    private lateinit var requestMapping: RequestMappingHandlerMapping;

    override fun getAllApi(scriptType: String): String {
        //找到所有外部暴露的controller请求，过滤调返回页面的请求。
        var path = requestMapping.handlerMethods.entries
                .filter { a -> !a.key.producesCondition.producibleMediaTypes.contains(MediaType.parseMediaType("text/html")) }
                .map { a ->
                    DynamicApiDto((a.value.bean.toString().removeSuffix("Controller")),
                            a.value.method.name,
                            a.key.patternsCondition.patterns,
                            a.key.consumesCondition.consumableMediaTypes,
                            a.key.methodsCondition.methods)
                }.toList();
        val script = when (scriptType) {
            ScriptType.normal -> genNormalWeb(path);
            ScriptType.angular -> genAngular(path);
            else -> genNormalWeb(path);
        }
        return script;
    }

    fun genNormalWeb(controllers: List<DynamicApiDto>): String {
        val sb = StringBuilder()
        sb.append("""
            app.service = app.service || {};
        """.trimIndent())
        for (item in controllers) {

            val js = """(function(){
                app.service['${item.name}']=app.service['${item.name}']||{};
app.service['${item.name}']['${item.serviceName}'] = function (data) {
    return afr({
        method: '${if (item.httpMethod.size > 0) item.httpMethod.first().name else "post"}',
        url: '${item.method.first()}',
        ${if (item.httpMethod.size > 0 && item.httpMethod.first().name.toLowerCase().equals("get")) "params: data" else "data: data"}
    });
};})();
"""
            sb.append(js);
        }
        return sb.toString();
    }

    fun genAngular(controllers: List<DynamicApiDto>): String {
        return "";
    }
}