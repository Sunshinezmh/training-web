package com.dmsdbj.integral.training.entity.ext;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;

@ApiModel(value = "培养计划项目表")
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class TrainingProgramModel extends BaseEntity implements Serializable{
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    @Column(name = "name")
    private String name;

    /**
     * 所属等级id
     */
    @ApiModelProperty(value = "所属等级id")
    @Column(name = "level_id")
    private String levelId;

    /**
     * 培养计划等级
     */
    @ApiModelProperty(value="等级")
    @Column(name = "level_order")
    private Integer levelOrder;

    /**
     * 该级别所获得积分
     */
    @ApiModelProperty(value = "该级别所获得积分")
    @Column(name = "integral")
    private String integral;

    /**
     * 项目介绍
     */
    @ApiModelProperty(value = "项目介绍")
    @Column(name = "description")
    private String description;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    @Column(name = "type")
    private String type;

    /**
     * 比率
     */
    @ApiModelProperty(value = "比率")
    @Column(name = "rate")
    private Double rate;



    /**
     * 插件名称
     */
    @ApiModelProperty(value = "插件名称")
    @Column(name = "plugin_name")
    private Double pluginName;



}
