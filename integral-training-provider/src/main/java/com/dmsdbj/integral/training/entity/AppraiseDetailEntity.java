package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * AppraiseDetail实体
 * 评价记录详细表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "AppraiseDetailEntity:评价记录详细表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_appraise_detail")
public class AppraiseDetailEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 评价人
	 */
    @ApiModelProperty(value = "评价人")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 被评价人
	 */
    @ApiModelProperty(value = "被评价人")
	@Column(name = "appraiser_id")
	private String appraiserId;

	/**
	 * 评价指标
	 */
    @ApiModelProperty(value = "评价指标")
	@Column(name = "sub_templet_id")
	private String subTempletId;

	/**
	 * 项目Id
	 */
    @ApiModelProperty(value = "项目Id")
	@Column(name = "program_id")
	private String programId;

	/**
	 * 得分
	 */
    @ApiModelProperty(value = "得分")
	@Column(name = "integral")
	private Double integral;




}
