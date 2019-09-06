package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * TrainingLevel实体
 * 培养计划等级表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "TrainingLevelEntity:培养计划等级表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_training_level")
public class TrainingLevelEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 升级方案id
	 */
    @ApiModelProperty(value = "升级方案id")
	@Column(name = "cases_id")
	private String casesId;

	/**
	 * 级别序列
	 */
	@ApiModelProperty(value = "级别序列",required = true)
	@Column(name = "level_order")
	private Integer levelOrder;

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


}
