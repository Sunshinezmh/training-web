package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.EvaluationEntity;
import com.dmsdbj.integral.training.provider.dao.train.EvaluationDao;
import com.dmsdbj.integral.training.provider.jpa.EvaluationJpa;
import com.dmsdbj.integral.training.provider.service.EvaluationService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * EvaluationService接口实现类
 * 评价他人表：
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("evaluationService")
public class EvaluationServiceImpl extends BaseServiceImpl<EvaluationEntity> implements EvaluationService {
	
	@Resource
    private EvaluationDao evaluationDao;

    @Resource
    private EvaluationJpa evaluationJpa;

    @Override
    protected JpaRepository<EvaluationEntity, String> getRealJpa() {
        return evaluationJpa;
    }


}
