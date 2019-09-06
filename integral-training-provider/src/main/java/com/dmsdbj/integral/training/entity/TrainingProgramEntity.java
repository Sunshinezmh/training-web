package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TrainingProgram实体
 * 培养计划项目表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@ApiModel(value = "TrainingProgramEntity:培养计划项目表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tit_training_program")
public class TrainingProgramEntity extends BaseEntity implements Serializable{
 	
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

//	/**
//	 * 审核标准
//	 */
//	@ApiModelProperty(value = "审核标准")
//	@Column(name = "audit_creteria")
//	private String auditCreteria;
//
//	/**
//	 * 项目说明
//	 */
//	@ApiModelProperty(value = "项目说明")
//	@Column(name = "project_instrution")
//	private String projectInstrution;

}
