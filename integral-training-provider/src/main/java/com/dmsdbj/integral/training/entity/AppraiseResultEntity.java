package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * AppraiseResult实体
 * 评价记录汇总表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "AppraiseResultEntity:评价记录汇总表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_appraise_result")
public class AppraiseResultEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 被评价人
	 */
    @ApiModelProperty(value = "被评价人")
	@Column(name = "appraiser_id")
	private String appraiserId;

	/**
	 * 评价人
	 */
    @ApiModelProperty(value = "评价人")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 评论内容
	 */
    @ApiModelProperty(value = "评论内容")
	@Column(name = "content")
	private String content;

	/**
	 * 总得分
	 */
    @ApiModelProperty(value = "总得分")
	@Column(name = "total_socre")
	private Double totalSocre;

	/**
	 * 模板
	 */
    @ApiModelProperty(value = "模板")
	@Column(name = "templet_id")
	private String templetId;

	/**
	 * program_id
	 */
    @ApiModelProperty(value = "program_id")
	@Column(name = "program_id")
	private String programId;


}
