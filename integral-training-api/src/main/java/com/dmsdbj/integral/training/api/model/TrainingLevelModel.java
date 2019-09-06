package com.dmsdbj.integral.training.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;


/**
 * 培养计划等级
 * @author 严文文
 * @since 0.0.1 2019-5-26 11:13:12
 */
@ApiModel(value = "TrainingLevelModel:培养计划等级表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class TrainingLevelModel {

        /**
         * 级别判断
         */
        @ApiModelProperty(value = "级别判断", required = true)

        private int levelOrderId;
        /**
         * 级别id
         */
        @ApiModelProperty(value = "级别id")
        @Column(name = "id")
        private String id;



        /**
         * 级别序列
         */
        @ApiModelProperty(value = "级别序列",required = true)
        @Column(name = "level_order")
        private String levelOrder;

        /**
         * 级别名字
         */
        @ApiModelProperty(value = "级别名字")
        @Column(name = "name")
        private String name;

        /**
         * 级别描述
         */
        @ApiModelProperty(value = "级别描述")
        @Column(name = "description")
        private String description;

        /**
         * 升级所需相对成长值
         */
        @ApiModelProperty(value = "升级所需相对成长值")
        @Column(name = "growth")
        private Integer growth;

        /**
         * 多少人在此等级
         */
        @ApiModelProperty(value = "多少人在此等级")
        @Column(name = "number_on")
        private Integer numberOn;

        /**
         * 多少人已经完成了此等级
         */
        @ApiModelProperty(value = "多少人已经完成了此等级")
        @Column(name = "number_over")
        private Integer numberOver;

        /**
         * 等级图片链接
         */
        @ApiModelProperty(value = "等级图片链接")
        @Column(name = "image_url")
        private String imageUrl;

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
         * 创建时间
         */
        @ApiModelProperty(value="创建时间")
        @Column(name="create_time")
        private String createTime;

        /**
         * 更新时间
         */
        @ApiModelProperty(value="更新时间")
        @Column(name="update_time")
        private String UpdateTime;

    }


