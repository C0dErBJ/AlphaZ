package com.alphaz.infrastructure.web.base

import com.alphaz.infrastructure.application.BaseAppService
import com.alphaz.infrastructure.application.dto.BaseDto
import com.alphaz.infrastructure.constant.AppConst
import com.alphaz.infrastructure.domain.model.base.BaseDO
import com.alphaz.infrastructure.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

/**
 *@Author: c0der
 *@Date: 上午1:35 2018/4/7
 *@Description:
 */
@RequestMapping("base")
open class BaseControllerImpl<D : BaseDto<ID>, T : BaseDO<T, ID>, ID, AppService : BaseAppService<D, T, ID, *, *>> : BaseController<D, T, ID, AppService> {
    @Autowired
    override lateinit var appService: AppService;


    @PostMapping("createbybatch")
    override fun createBatch(t: List<D>): MutableList<D> {
        return appService.createOrUpdate(t)
    }

    @PostMapping("create")
    override fun create(t: D): D {
        return appService.createOrUpdate(t);
    }

    @PutMapping("update")
    override fun update(t: D): D {
        if (t.id == null) {
            throw BusinessErrorException(appService.l.getMessage("IdMustNotBeNull"))
        }
        val target = appService.getDetailById(t.id)
                ?: throw BusinessErrorException(appService.l.getMessage("CouldNotFindTheEntity"))
        return appService.createOrUpdate(t);
    }

    @GetMapping("/{id}")
    override fun getDetailById(id: ID): D? {
        return appService.getDetailById(id);
    }

    @GetMapping("/getlistbypage")
    override fun getListByPage(specification: Specification<T>?, @PageableDefault(sort = [(AppConst.defaltSort)]) pageable: Pageable): Page<D> {
        return appService.getListByPage(specification, pageable)
    }

    @DeleteMapping("/{id}")
    override fun deleteById(id: ID) {
        return appService.deleteById(id);
    }
}