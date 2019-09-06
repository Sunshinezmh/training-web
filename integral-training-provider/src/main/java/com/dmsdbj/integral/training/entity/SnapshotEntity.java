package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Snapshot实体
 * 培养计划英语快照表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "SnapshotEntity:培养计划英语快照表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_snapshot")
public class SnapshotEntity extends BaseEntity implements Serializable{
 	
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
	 * 图片URL
	 */
    @ApiModelProperty(value = "图片URL")
	@Column(name = "url")
	private String url;


}
