package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * User实体
 * 积分用户表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 14:31:35
 */
@ApiModel(value = "UserEntity:积分用户表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_user")
public class UserEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 用户代码（登录账号）
	 */
    @ApiModelProperty(value = "用户代码（登录账号）")
	@Column(name = "user_code")
	private String userCode;

	/**
	 * 用户姓名（来源于权限，冗余）
	 */
    @ApiModelProperty(value = "用户姓名（来源于权限，冗余）")
	@Column(name = "user_name")
	private String userName;

	/**
	 * 级别id
	 */
    @ApiModelProperty(value = "级别id")
	@Column(name = "level_id")
	private String levelId;

	/**
	 * 给与加分权重
	 */
    @ApiModelProperty(value = "给与加分权重")
	@Column(name = "giving_weight")
	private Integer givingWeight;

	/**
	 * 有多少分可以奖励别人
	 */
    @ApiModelProperty(value = "有多少分可以奖励别人")
	@Column(name = "giving_integral")
	private Integer givingIntegral;

	/**
	 * 总积分
	 */
    @ApiModelProperty(value = "总积分")
	@Column(name = "integral")
	private Integer integral;

	/**
	 * 总成长值
	 */
    @ApiModelProperty(value = "总成长值")
	@Column(name = "growth")
	private Integer growth;

	/**
	 * 是否允许减分(0-不允许,1-允许)
	 */
    @ApiModelProperty(value = "是否允许减分(0-不允许,1-允许)")
	@Column(name = "is_permit_minus")
	private Integer isPermitMinus;

	/**
	 * 租户id
	 */
    @ApiModelProperty(value = "租户id")
	@Column(name = "tenant_id")
	private String tenantId;

	/**
	 * 头像链接地址
	 */
    @ApiModelProperty(value = "头像链接地址")
	@Column(name = "image_url")
	private String imageUrl;

	/**
	 * 是否开启将自己积分给别人（0不开启，1开启）
	 */
	@ApiModelProperty(value = "是否开启将自己积分给别人（0不开启，1开启）",required = true)
	@Column(name = "is_giving_self")
	private Integer isGivingSelf;

	/**
	 * 是否开启可以让别人查看自己的成长
	 */
    @ApiModelProperty(value = "是否开启可以让别人查看自己的成长")
	@Column(name = "is_look_self")
	private Integer isLookSelf;


}
