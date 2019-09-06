package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.AppraiseDetailEntity;
import com.dmsdbj.integral.training.provider.dao.train.AppraiseDetailDao;
import com.dmsdbj.integral.training.provider.jpa.AppraiseDetailJpa;
import com.dmsdbj.integral.training.provider.service.AppraiseDetailService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * AppraiseDetailService接口实现类
 * 评价记录详细表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("appraiseDetailService")
public class AppraiseDetailServiceImpl extends BaseServiceImpl<AppraiseDetailEntity> implements AppraiseDetailService {
	
	@Resource
    private AppraiseDetailDao appraiseDetailDao;

    @Resource
    private AppraiseDetailJpa appraiseDetailJpa;

    @Override
    protected JpaRepository<AppraiseDetailEntity, String> getRealJpa() {
        return appraiseDetailJpa;
    }


}
