package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.entity.AppraiseResultEntity;
import com.dmsdbj.cloud.tool.business.BaseService;
import com.dmsdbj.integral.training.entity.ext.ScoreModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * AppraiseResultService接口
 * 评价记录汇总表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
public interface AppraiseResultService extends BaseService<AppraiseResultEntity> {

    /**
     * 插入评分详情，包括详情表和总分记录表
     * @author 王婷婷
     * @since 2019年6月9日10:52:59
     */
     List<ScoreModel> updateScore(List<ScoreModel> scoreModel);


}
