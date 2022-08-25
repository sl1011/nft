package com.shitouren.core.bean.vo.user;

import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.eums.ImgEnum;
import lombok.Data;

@Data
public class TeamVo {
    private String avatar;
    private String nickname;
    private String phone;

    public TeamVo(Users user){
        this.avatar = ImgEnum.img.getUrl() + user.getHeadPrtraits();
        this.nickname = user.getNickName();
        this.phone = user.getPhoneNumber();
    }
    public TeamVo(){

    }
}
