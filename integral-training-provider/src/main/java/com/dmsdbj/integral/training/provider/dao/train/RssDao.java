package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.training.entity.RssEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RssDao接口
 * RSS订阅地址表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 15:10:16
 */
@Mapper
@Repository("rssDao")
public interface RssDao {

    RssEntity queryByUserId(String userId);
}
