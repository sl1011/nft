package com.shitouren.core.bean.param.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autho： 王涛
 * @DATE： 2020/5/18 14:18
 */
@Data
@ApiModel(value = "propertyVerificationCodeParam")
public class VerificationCodeParam {
    @ApiModelProperty(value = "手机号码", required = true)
    private String phone;

    @NotBlank(message = "请输入验证类型")
    @ApiModelProperty(
            value = "验证码类型  注册=reg_code_ , " +
                    "忘记密码 = forget_pwd_code_ , " +
                    "校验手机号 = check_code_ , " +
                    "交易密码 = update_trade_pwd_,",

//            注册("reg_code_"),d
//            忘记密码("forget_pwd_code_"),
//            校验手机号("check_code_"),
//            交易密码("update_trade_pwd_");

            required = true
    )
    private String type;
}
