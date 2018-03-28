package com.alphaz.infrastructure.domain.filter

import com.alphaz.infrastructure.domain.filter.annotation.DataState
import com.alphaz.infrastructure.constant.State
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Pointcut
import org.hibernate.Session
import org.slf4j.LoggerFactory
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
open class DataStateFilter {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @PersistenceContext
    private val entityManager: EntityManager? = null


    @Pointcut("execution(* com.alphaz.infrastructure.domain.service.DomainService+.*(..))")
    fun baseRepository() {

    }

    @Pointcut("@within(dataStateClass)")
    fun stateFilterClass(dataStateClass: DataState) {

    }

    @Pointcut("@annotation(dataStateMethod)")
    fun stateFilterMethod(dataStateMethod: DataState) {

    }

    @Around(value = "stateFilterClass(dataStateClass) && baseRepository()")
    @Throws(Throwable::class)
    fun handleClassFilter(joinPoint: ProceedingJoinPoint, dataStateClass: DataState): Any? {
        log.info("start stateFilter Class")
        if (!dataStateClass.enable) {
            log.warn("ignore stateFilter Class")
            val obj = joinPoint.proceed()
            return obj
        }
        var session: Session? = null
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
        log.info("end stateFilter Class")
        return obj

    }

    @Around(value = "stateFilterMethod(dataStateMethod) && baseRepository()")
    @Throws(Throwable::class)
    fun handleMethodFilter(joinPoint: ProceedingJoinPoint, dataStateMethod: DataState): Any? {
        log.info("start stateFilter Method")
        if (!dataStateMethod.enable) {
            log.warn("ignore stateFilter Method")
            val obj = joinPoint.proceed()
            return obj
        }
        var session: Session? = null
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
        log.info("end stateFilter Method")
        return obj

    }
}