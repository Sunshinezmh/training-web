package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * AppraiseTemplet实体
 * 模板-评价分类-评价指标树
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "AppraiseTempletEntity:模板-评价分类-评价指标树")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_appraise_templet")
public class AppraiseTempletEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 名称
	 */
    @ApiModelProperty(value = "名称")
	@Column(name = "name")
	private String name;

	/**
	 * 类型（模板0，评价分类1，评价指标2）
	 */
    @ApiModelProperty(value = "类型（模板0，评价分类1，评价指标2）")
	@Column(name = "type")
	private Integer type;

	/**
	 * 父节点
	 */
    @ApiModelProperty(value = "父节点")
	@Column(name = "parent_id")
	private String parentId;

	/**
	 * 权重
	 */
    @ApiModelProperty(value = "权重")
	@Column(name = "weight")
	private Integer weight;

	/**
	 * 模板id
	 */
    @ApiModelProperty(value = "模板id")
	@Column(name = "templet_id")
	private String templetId;


}
