package com.dmsdbj.integral.training.entity.ext;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 培养计划加分时间配置表Model
* @author 严文文
* @version 0.0.1
* @since 2019-1-29 21:23:04
*/
@ApiModel(value = "培养计划加分时间配置表")
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DeployModel extends BaseEntity implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private String id;

    /**
     * 配置
     */
    @ApiModelProperty(value = "配置", required = true)
    private String configure;



}
