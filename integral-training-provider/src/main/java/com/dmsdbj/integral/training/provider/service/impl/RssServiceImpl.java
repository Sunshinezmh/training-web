package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.RssEntity;
import com.dmsdbj.integral.training.provider.dao.train.RssDao;
import com.dmsdbj.integral.training.provider.jpa.RssJpa;
import com.dmsdbj.integral.training.provider.service.RssService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RssService接口实现类
 * RSS订阅地址表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 15:10:16
 */
@Slf4j
@Service("rssService")
public class RssServiceImpl extends BaseServiceImpl<RssEntity> implements RssService {
	
	@Resource
    private RssDao rssDao;

    @Resource
    private RssJpa rssJpa;

    @Override
    protected JpaRepository<RssEntity, String> getRealJpa() {
        return rssJpa;
    }


}
