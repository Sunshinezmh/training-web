package com.dmsdbj.integral.training.entity.ext;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 评分条记录详情
 *
 */
@NoArgsConstructor
@Data
@Accessors
@ToString
public class ScoreDetailedModel {
    /**
     * 被评价人id
     */
    @ApiModelProperty(value = "被评价人" ,required=true)
    private String appraiseId;

    /**
     * 评价指标id
     */
    @ApiModelProperty(value = "评价指标",required = true)
    private String subTempletId;

    /*
     * 用户id
     * */
    @ApiModelProperty(value = "得分",required = true)
    private double integral;

    @ApiModelProperty(value = "详细评分Id",required = true)
    private String detailId;

}
