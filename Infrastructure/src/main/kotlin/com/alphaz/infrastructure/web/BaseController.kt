package com.alphaz.infrastructure.web

import com.alphaz.infrastructure.application.BaseAppService
import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.constant.AppConst
import com.alphaz.infrastructure.domain.model.base.BaseDO
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
@RequestMapping("base")
open class BaseController<D : BaseDto, T : BaseDO<T, ID>, ID, AppService : BaseAppService<D, T, ID, *>> {
    @Autowired
    private lateinit var appService: AppService;

    @PostMapping("createbybatch")
    fun createOrUpdateBatch(t: List<D>): MutableList<D> {
        return appService.createOrUpdate(t)
    }

    @PostMapping("create")
    fun createOrUpdate(t: D): D {
        return appService.createOrUpdate(t);
    }

    @GetMapping("/{id}")
    fun getDetailById(id: ID): D? {
        return appService.getDetailById(id);
    }

    @GetMapping("/getlistbypage")
    fun getListByPage(specification: Specification<T>?, @PageableDefault(sort = [(AppConst.defaltSort)]) pageable: Pageable): Page<D> {
        return appService.getListByPage(specification, pageable)
    }

    @DeleteMapping("/{id}")
    fun deleteById(id: ID) {
        return appService.deleteById(id);
    }


}