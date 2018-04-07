package com.alphaz.infrastructure.web.base

import com.alphaz.infrastructure.application.BaseAppService
import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.constant.AppConst
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.web.annotation.WrapResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 *@Author: c0der
 *@Date: 下午3:11 2018/4/6
 *@Description:
 */
interface BaseController<D : BaseDto<ID>, T : BaseDO<T, ID>, ID, out AppService : BaseAppService<D, T, ID, *, *>> {
    val appService: AppService;

    fun createBatch(t: List<D>): MutableList<D>
    fun create(t: D): D
    fun update(t:D):D
    fun getDetailById(id: ID): D?
    fun getListByPage(specification: Specification<T>?, @PageableDefault(sort = [(AppConst.defaltSort)]) pageable: Pageable): Page<D>
    fun deleteById(id: ID)


}