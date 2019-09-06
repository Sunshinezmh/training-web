package com.dmsdbj.integral.training.entity.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.List;

/**
 * 评价分数以及内容汇总
 */
@ApiModel(value = "ScoreModel:评分")
@NoArgsConstructor
@Data
@Accessors
@ToString
public class ScoreModel {
    /*
     * 被评价人id
     * */
    @ApiModelProperty(value = "被评价人",required = true)
    private String appraiserId;

    /*
     * 评价人id
     * */
    @ApiModelProperty(value = "评价人",required = true)
    private String userId;

    /*
     * 评价内容
     * */
    @ApiModelProperty(value = "评价内容",required = true)
    private String content;


    /**
     * 详情model
     */
    private List<ScoreDetailedModel> scoreDetailedModels;


    /*
     * 总得分
     * */
    @ApiModelProperty(value = "总得分",required = true)
    private Double totalSocre;

    /*
     * 总分表id
     * */
    @ApiModelProperty(value = "总分表id",required = true)
    private String resultId;

    /*
     * 模板
     * */
    @ApiModelProperty(value = "模板",required = true)
    private String templetId;

    /*
     * 操作人
     * */
    @ApiModelProperty(value = "操作人",required = true)
    private String operator;

    /*
     * 创建人
     * */
    @ApiModelProperty(value = "创建人",required = true)
    private String creator;

    /**
     * 项目Id
     */
    @ApiModelProperty(value = "项目Id")
    @Column(name = "program_id")
    private String programId;
}
