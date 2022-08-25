package com.shitouren.core.bean.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LikeTaskParam {

    /**
     * 总任务量
     */
    @ApiModelProperty("总任务量")
    private Integer total;

    /**
     * 1:抖音 2:快手
     */
    @ApiModelProperty("1:抖音 2:快手")
    private Integer type;

    /**
     *
     */
    private String link1;

    /**
     *
     */
    private String link2;

    /**
     *
     */
    private String link3;

}
