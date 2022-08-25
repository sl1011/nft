package com.shitouren.core.bean.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autho： 王涛
 * @DATE： 2019/11/29 11:35
 */
@Data
public class UserLoginParam {

    @NotBlank(message = "请输入账号")
    private String userAccount;//登录账户

 /*   @NotBlank(message = "请输入密码")
    @ApiModelProperty("密码")
    private String userPassword;//用户密码*/

    @ApiModelProperty("验证码")
    @NotBlank(message = "请输入验证码")
    private String code;

    @ApiModelProperty("uid")
    private String uid;

       /* @ApiModelProperty("邀请码")
    private String registerCode;*/
}
