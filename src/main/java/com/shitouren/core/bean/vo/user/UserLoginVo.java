package com.shitouren.core.bean.vo.user;


import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.utils.StringUtil;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Autho： 王涛
 * @DATE： 2020/5/7 15:25
 */
@Data
public class UserLoginVo implements Serializable {
    private static final long serialVersionUID = -1316500030945618923L;
    private Integer pid;

    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 网络昵称：业务必填，可默认设为真实姓名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态：0正常，1禁用
     */
    private String userStatus;


    public UserLoginVo(Users user) {
        this.pid = user.getUserId();
        this.loginAccount = StringUtil.convertStrIfNull(user.getPhoneNumber());
        this.nickname = StringUtil.convertStrIfNull(user.getNickName());
        this.avatar = StringUtil.convertStrIfNull(ImgEnum.img.getUrl() +user.getHeadPrtraits());
        this.userStatus = user.getStatusId();
    }


    public UserLoginVo() {
    }
}
