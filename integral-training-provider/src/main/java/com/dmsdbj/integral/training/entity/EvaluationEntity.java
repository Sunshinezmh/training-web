package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Evaluation实体
 * 评价他人表：
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "EvaluationEntity:评价他人表：")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_evaluation")
public class EvaluationEntity extends BaseEntity implements Serializable{
 	
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
	 * 项目id
	 */
    @ApiModelProperty(value = "项目id")
	@Column(name = "program_id")
	private String programId;

	/**
	 * 图片URL
	 */
    @ApiModelProperty(value = "图片URL")
	@Column(name = "blog_id")
	private String blogId;

	/**
	 * 单条点赞 0表示点赞，1表示取消
	 */
    @ApiModelProperty(value = "单条点赞 0表示点赞，1表示取消")
	@Column(name = "is_like")
	private Integer isLike;


}
