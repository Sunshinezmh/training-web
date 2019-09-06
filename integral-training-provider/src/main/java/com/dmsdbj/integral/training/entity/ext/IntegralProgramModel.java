package com.dmsdbj.integral.training.entity.ext;/**
 * Created by 李娜 on 2018-09-29.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 项目完成加分model
 * @author 李娜
 * @version 0.0.1
 * @since 2018-09-29 15:00
 */
@ApiModel(value = "LotteryModel:加分表")
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralProgramModel {
    /**
     * 插件id
     */
    @ApiModelProperty(value = "插件id", required = true)
    private String pluginId;

    /**
     * 被加分人id,以及完成项目的用户id
     */
    @ApiModelProperty(value = "被加分人id", required = true)
    private String userId;


    /**
     * 被加分人id   和service中userentity里userId共用
     */
    @ApiModelProperty(value = "接收id数组", required = true)
    private String[] idInfo = new String[500];

    /**
     * 积分
     */
    @ApiModelProperty(value = "积分", required = true)
    private Integer integral;

    /**
     * 类型key
     */
    @ApiModelProperty(value = "类型key", required = true)
    private String typeKey;

    /**
     * 产生原因
     */
    @ApiModelProperty(value = "产生原因", required = true)
    private String reason;

    /**
     * 项目id
     */
    @ApiModelProperty(value="完成项目的id" ,required=true)
    private String programId;

    /**
     * 用户项目主键id
     */
    @ApiModelProperty(value="用户项目主键id")
    private String trainingUserId;

    /**
     * 登录用户
     */
    @ApiModelProperty(value="登录用户名")
    private String loginUser;

}
