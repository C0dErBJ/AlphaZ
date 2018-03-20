package com.alphaz.infrastructure.domain.model

import com.alphaz.infrastructure.constant.Status
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 *@Author: c0der
 *@Date: 下午2:34 2018/3/8
 *@Description:
 */
@ApiModel(description = "业务对象")
data class ResponseModel<out T>(@ApiModelProperty(value = "返回状态", required = true)
                                val state: Status = Status.SUCCESS,
                                @ApiModelProperty(value = "消息提示", required = true)
                                val error: ErrorInfo?,
                                @ApiModelProperty(value = "返回数据", required = true)
                                val data: T?,
                                @ApiModelProperty(value = "http状态值", required = true)
                                val httpStatus: String = "200") {
    constructor(error: ErrorInfo) : this(Status.FAILED, error, null, "200")
    constructor(state: Status, error: ErrorInfo, data: T?) : this(state, error, data, "200")
    constructor() : this(Status.SUCCESS, null, null, "200")
}


