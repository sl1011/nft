package com.shitouren.core.bean.param;

import io.lettuce.core.codec.StringCodec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RealNameParam {
    /*@ApiModelProperty("银行卡卡号")
    private String accountNo;*/

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号码")
    private String idCardCode;

  /*  @ApiModelProperty("银行预留手机号码")
    private String bankPreMobile;

    @ApiModelProperty("开户行")
    private String bankname;*/


}
