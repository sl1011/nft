package com.shitouren.core.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CitySiteParam {
    @ApiModelProperty(value = "真实姓名")
    private String name;

    /**
     *
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     *
     */
    @ApiModelProperty("微信号")
    private String weixin;

    /**
     *
     */
    @ApiModelProperty("QQ")
    private String qq;

    /**
     *
     */
    @ApiModelProperty("详细地址")
    private String address;
}
