package com.dmsdbj.integral.training.entity;

import com.dmsdbj.cloud.tool.business.BaseEntity;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Rss实体
 * RSS订阅地址表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 15:10:16
 */
@ApiModel(value = "RssEntity:RSS订阅地址表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tik_rss")
public class RssEntity extends BaseEntity implements Serializable{
 	
	/**
	 * RSS订阅地址
	 */
    @ApiModelProperty(value = "RSS订阅地址")
	@Column(name = "rss_url")
	private String rssUrl;

	/**
	 * 用户Id
	 */
    @ApiModelProperty(value = "用户Id")
	@Column(name = "user_id")
	private String userId;


}
