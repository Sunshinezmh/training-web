package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.integral.training.entity.BlogEntity;
import com.dmsdbj.cloud.tool.business.BaseService;

/**
 * BlogService接口
 * 博客链接表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
public interface BlogService extends BaseService<BlogEntity> {

    /**
     * 定时获取最新的所有博客
     * @author:严文文
     * @since:2019-6-6 10:25:07
     * @return
     */
    boolean updateAllBlog();

}
