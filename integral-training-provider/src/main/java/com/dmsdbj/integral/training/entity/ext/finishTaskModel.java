package com.dmsdbj.integral.training.entity.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户完成的任务实体（属性两个）
 * @auther:刘兵
 * @version: 0.0.1
 * @since：2019/6/18 20:24
 */
@ApiModel(value = "用户完成的任务实体（属性两个）")
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class finishTaskModel {
    @ApiModelProperty(value = "任务等级", required = true)
    private String project;

    @ApiModelProperty(value = "任务名称", required = true)
    private String name;
}
