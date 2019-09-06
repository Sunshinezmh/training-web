package com.dmsdbj.integral.training.api.model;/**
 * Created by 李娜 on 2018-09-29.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.persistence.Column;


/**
 * 用户所在培养计划项目
 * @author 李娜
 * @since 0.0.1 2018-09-29 10:14
 */
@ApiModel(value = "TrainingModel:用户所在培养计划项目表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class TrainingModel {
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    @Column(name ="id")
    private String id;


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
     * 是否完成
     */
    @ApiModelProperty(value = "是否完成")
    @Column(name = "is_finished")
    private Integer isFinished;

    /**
     * 创建者
     */
    @ApiModelProperty(value="创建者")
    @Column(name="creator")
    private String creator;

    /**
     * 操作者
     */
    @ApiModelProperty(value="操作者")
    @Column(name="operator")
    private String operator;

    /**
     * 比率
     */
    @ApiModelProperty(value="比率")
    @Column(name="rate")
    private String rate;
}
