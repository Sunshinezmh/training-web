package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * TrainingUser实体
 * 人员与培养计划关系表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@ApiModel(value = "TrainingUserEntity:人员与培养计划关系表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tit_training_user")
public class TrainingUserEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 项目id
	 */
    @ApiModelProperty(value = "项目id")
	@Column(name = "program_id")
	private String programId;

	/**
	 * 是否完成
	 */
    @ApiModelProperty(value = "是否完成")
	@Column(name = "is_finished")
	private Integer isFinished;


}
