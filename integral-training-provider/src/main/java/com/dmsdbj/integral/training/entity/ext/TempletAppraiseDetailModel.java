package com.dmsdbj.integral.training.entity.ext;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class TempletAppraiseDetailModel extends BaseEntity implements Serializable {
    /**
     * 名称
     */
    @ApiModelProperty(value = "评分详情id")
    private String detailId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类型（模板0，评价分类1，评价指标2）
     */
    @ApiModelProperty(value = "类型（模板0，评价分类1，评价指标2）")
    private Integer type;

    /**
     * 父节点
     */
    @ApiModelProperty(value = "父节点")
    private String parentId;

    /**
     * 权重
     */
    @ApiModelProperty(value = "权重")
    private Integer weight;

    /**
     * 模板id
     */
    @ApiModelProperty(value = "模板id")
    private String templetId;

    /**
     * 分值
     */
    @ApiModelProperty(value = "分值")
    private double integral;
}
