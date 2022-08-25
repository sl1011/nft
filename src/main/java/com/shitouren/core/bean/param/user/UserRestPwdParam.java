package com.shitouren.core.bean.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/2 13:31
 */
@Data
@ApiModel
public class UserRestPwdParam {

    @NotBlank(message = "请输入手机号码")
    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("验证码")
    @NotBlank(message = "请输入验证码")
    private String code;

    @ApiModelProperty("密码")
    @NotBlank(message = "请输入密码")
    private String password;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "请确认密码")
    private String password2;
}
