package com.shitouren.core.bean.vo.user;


import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.utils.StringUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @Autho： 王涛
 * @DATE： 2020/5/7 15:25
 */
@Data
public class UserLoginWx implements Serializable {
    private static final long serialVersionUID = -1316500030945618923L;

    private String userIp;
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

    private Integer userId;

    public UserLoginWx(Users user) {
        this.userId = user.getUserId();
        this.nickname = StringUtil.convertStrIfNull(user.getNickName());
        this.userStatus = user.getStatusId();
    }


    public UserLoginWx() {
    }
}
