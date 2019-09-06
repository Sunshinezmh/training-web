package com.dmsdbj.integral.training.entity.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户当前等级项目表
 * @param
 * @auther:刘兵
 * @version: 0.0.1
 * @since：2019/6/11 15:31
 */
@ApiModel(value = "用户当前等级项目表")
@NoArgsConstructor
@Data
@Accessors(chain=true)
@ToString(callSuper = true)
public class currentProjectModel {
    @ApiModelProperty(value = "当前等级" ,required = true)
    private String root;

    @ApiModelProperty(value = "项目名称", required = true)
    private String name;
}
