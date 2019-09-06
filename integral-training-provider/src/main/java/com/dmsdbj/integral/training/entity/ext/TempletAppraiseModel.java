package com.dmsdbj.integral.training.entity.ext;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Data
public class TempletAppraiseModel  extends BaseEntity implements Serializable {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Column(name = "name")
    private String name;

    /**
     * 类型（模板0，评价分类1，评价指标2）
     */
    @ApiModelProperty(value = "类型（模板0，评价分类1，评价指标2）")
    @Column(name = "type")
    private Integer type;

    /**
     * 父节点
     */
    @ApiModelProperty(value = "父节点")
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 权重
     */
    @ApiModelProperty(value = "权重")
    @Column(name = "weight")
    private Integer weight;

    /**
     * 模板id
     */
    @ApiModelProperty(value = "模板id")
    @Column(name = "templet_id")
    private String templetId;

    public List<TempletAppraiseDetailModel> templetAppraiseEntityList;

}
