package com.shitouren.core.bean.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 继承此类，然后使用 {@link com.shitouren.core.annotation.GetLoginUser }  则可以自动吧用户相关对象注入到请求参数中
 *
 * @Autho： 王涛
 * @DATE： 2019/8/20 9:36
 */
@Data
public class SysUserParam implements Serializable {
    private static final long serialVersionUID = 5466686651799895467L;

    /**
     * 登录用户主键
     */
    @ApiModelProperty(hidden = true)
    private Integer logUserPid;

    /**
     * 登录账号
     */
    @ApiModelProperty(hidden = true)
    private String loginAccount;


    /**
     * 登录账号
     */
    @ApiModelProperty(hidden = true)
    private String userIp;

    @ApiModelProperty(hidden = true)
    private String token;


}
