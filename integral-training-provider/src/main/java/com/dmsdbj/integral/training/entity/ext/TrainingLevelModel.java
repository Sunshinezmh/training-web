package com.dmsdbj.integral.training.entity.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.dmsdbj.cloud.tool.business.BaseEntity;

import java.io.Serializable;

/**
 * 培养计划等级表Model
 * @author 严文文
 * @version 0.0.1
 * @since 2019-1-29 21:23:04
 */
@ApiModel(value = "培养计划等级表Model")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TrainingLevelModel   extends BaseEntity implements Serializable  {
    /**
     * 级别id
     */
    @ApiModelProperty(value = "级别id", required = true)
    private String id;

    /**
     * 级别判断
     */
    @ApiModelProperty(value = "级别判断", required = true)

    private int levelOrderId;
    /**
     * 级别序列
     */
    @ApiModelProperty(value = "级别序列", required = true)

    private String levelOrder;

    /**
     * 级别名字
     */
    @ApiModelProperty(value = "级别名字")

    private String name;

    /**
     * 级别描述
     */
    @ApiModelProperty(value = "级别描述")

    private String description;

    /**
     * 升级所需成长值
     */
    @ApiModelProperty(value = "升级所需成长值")
    private String growth;
    /**
     * 多少人在此等级
     */
    @ApiModelProperty(value = "多少人在此等级")
    private String numberOn;

    /**
     * 多少人已经完成了此等级
     */
    @ApiModelProperty(value = "多少人已经完成了此等级")
    private String numberOver;
}
