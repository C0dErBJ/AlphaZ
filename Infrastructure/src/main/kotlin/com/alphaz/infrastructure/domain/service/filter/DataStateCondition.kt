package com.alphaz.infrastructure.domain.service.filter

import com.alphaz.infrastructure.domain.service.filter.annotation.StateFilter
import com.alphaz.infrastructure.domain.constant.common.State
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.hibernate.Session
import org.omg.CORBA.Object
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.AnnotationUtils
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 *@Author: c0der
 *@Date: 下午4:01 2018/3/10
 *@Description: 全局逻辑删除过滤
 * 参考：https://stackoverflow.com/questions/45080171/appending-custom-conditions-on-spring-data-jpa-repository-method-queries
 */
@Aspect
@Component
open class DataStateCondition {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @PersistenceContext
    private val entityManager: EntityManager? = null


    @Pointcut("execution(* com.alphaz.infrastructure.domain.service.common.BaseService+.*(..))")
    fun baseRepository() {

    }

    @Pointcut("@within(stateFilterClass)")
    fun stateFilterClass(stateFilterClass: StateFilter) {

    }

    @Pointcut("@annotation(stateFilterMethod)")
    fun stateFilterMethod(stateFilterMethod: StateFilter) {

    }

    @Around(value = " stateFilterClass(stateFilterClass) && baseRepository()")
    @Throws(Throwable::class)
    @Synchronized
    fun handleClassFilter(joinPoint: ProceedingJoinPoint, stateFilterClass: StateFilter): Any? {
        log.info("stateFilter Class")
        if (!stateFilterClass.enable) {
            log.warn("ignore stateFilter Class")
            val obj = joinPoint.proceed()
            return obj
        }
        var session: Session? = null
        var sign = joinPoint.signature as MethodSignature;
        var annotation = AnnotationUtils.findAnnotation(sign.method, StateFilter::class.java);
        try {
            //从当前事务获取session
            session = entityManager!!.unwrap(Session::class.java)
            //开启过滤
            val filter = session!!.enableFilter("dataStateCondition")
            filter.setParameter("state", State.ACTIVE.value);
        } catch (ex: Exception) {
            log.error("全局数据state过滤异常:" + ex.message)

        }

        val obj = joinPoint.proceed()

        if (session != null) {
            session.disableFilter("dataStateCondition")
        }
        return obj

    }

    @Around(value = " stateFilterMethod(stateFilterMethod) && baseRepository()")
    @Throws(Throwable::class)
    @Synchronized
    fun handleMethodFilter(joinPoint: ProceedingJoinPoint, stateFilterMethod: StateFilter): Any? {
        log.info("stateFilter Method")
        if (!stateFilterMethod.enable) {
            log.warn("ignore stateFilter Method")
            val obj = joinPoint.proceed()
            return obj
        }
        var session: Session? = null
        var sign = joinPoint.signature as MethodSignature;
        var annotation = AnnotationUtils.findAnnotation(sign.method, StateFilter::class.java);
        try {
            //从当前事务获取session
            session = entityManager!!.unwrap(Session::class.java)
            //开启过滤
            val filter = session!!.enableFilter("dataStateCondition")
            filter.setParameter("state", State.ACTIVE.value);
        } catch (ex: Exception) {
            log.error("全局数据state过滤异常:" + ex.message)

        }

        val obj = joinPoint.proceed()

        if (session != null) {
            session.disableFilter("dataStateCondition")
        }
        return obj

    }
}