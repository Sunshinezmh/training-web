package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Blog实体
 * 博客链接表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@ApiModel(value = "BlogEntity:博客链接表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_blog")
public class BlogEntity extends BaseEntity implements Serializable{
 	
	/**
	 * 博客链接
	 */
    @ApiModelProperty(value = "博客链接")
	@Column(name = "blog_url")
	private String blogUrl;

	/**
	 * 博客作者Id
	 */
    @ApiModelProperty(value = "博客作者Id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 博客名
	 */
    @ApiModelProperty(value = "博客名")
	@Column(name = "blog_name")
	private String blogName;

	/**
	 * 发表时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "发表时间")
	@Column(name = "issuing_time")
	private Date issuingTime;

	/**
	 * 内容
	 */
    @ApiModelProperty(value = "内容")
	@Column(name = "content")
	private String content;


}
