package com.dmsdbj.integral.training.entity.ext;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * 根据摸板评价人给被评价人的总分
 */
@ApiModel(value = "FindTempletByMoreIdModel")
@NoArgsConstructor
@Data
@Accessors
@ToString
public class FindTempletByMoreIdModel {
    /**
     * 评价人
     */
    @ApiModelProperty(value = "评价人")
    @Column(name = "user_id")
    private String userId;

    /**
     * 被评价人
     */
    @ApiModelProperty(value = "被评价人")
    @Column(name = "appraiser_id")
    private String appraiserId;

    /**
     * 项目Id
     */
    @ApiModelProperty(value = "项目Id")
    @Column(name = "program_id")
    private String programId;

    /**
     * 模板id
     */
    @ApiModelProperty(value = "模板id")
    @Column(name = "templet_id")
    private String templetId;

    /**
     * 项目类型（模板名字）
     */
    @ApiModelProperty(value = "项目类型")
    private  String programType;



}
