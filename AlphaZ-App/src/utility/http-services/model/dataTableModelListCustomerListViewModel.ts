/**
 * AlphaZ  Api
 * AlphaZ Swagger Api
 *
 * OpenAPI spec version: V1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { CustomerListViewModel } from './customerListViewModel';


/**
 * 前端datatable数据结构
 */
export interface DataTableModelListCustomerListViewModel {
    /**
     * 数据list
     */
    data: Array<CustomerListViewModel>;
    /**
     * 防止crsf，由datatable生成
     */
    draw: number;
    /**
     * 过滤后数据条数
     */
    recordsFiltered: number;
    /**
     * 总共数据条数
     */
    recordsTotal: number;
}
