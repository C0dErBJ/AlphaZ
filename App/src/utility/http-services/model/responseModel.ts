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


/**
 * 业务对象
 */
export interface ResponseModel {
    /**
     * 返回数据
     */
    data: any;
    /**
     * http状态值
     */
    httpStatus: string;
    /**
     * 消息提示
     */
    message: string;
    /**
     * 返回状态
     */
    state: ResponseModel.StateEnum;
}
export namespace ResponseModel {
    export type StateEnum = '0' | '1';
    export const StateEnum = {
        _0: '0' as StateEnum,
        _1: '1' as StateEnum
    }
}
