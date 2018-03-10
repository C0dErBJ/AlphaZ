package com.alphaz.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.app.controller
 * User: C0dEr
 * Date: 2017/7/26
 * Time: 下午2:02
 * Description:This is a class of com.alphaz.app.controller
 */
@RestController
@RequestMapping("product")
@Api(value = "product")
public class ProductController {

//    @GetMapping("template")
//    @ApiOperation(value = "获取所有产品模板", produces = "application/json")
//    public ResponseModel getAllTemplate() {
//        return this.productService.getAllTemplate();
//    }
//
//    @GetMapping("search")
//    @ApiOperation(value = "获取产品列表", produces = "application/json")
//    public DataTableModel<List<ProductListViewModel>> getProducts(ProductSearchModel model) {
//        return this.productService.searchProduct(model.keyword, model.length, model.start, model.draw);
//    }

}
