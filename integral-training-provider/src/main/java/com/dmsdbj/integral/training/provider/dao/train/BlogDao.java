package com.dmsdbj.integral.training.provider.dao.train;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * BlogDao接口
 * 博客链接表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Mapper
@Repository("blogDao")
public interface BlogDao {

    boolean deleteByUserId(String userId);

}
