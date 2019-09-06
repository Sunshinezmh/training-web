package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.AppraiseDetailEntity;
import com.dmsdbj.integral.training.entity.AppraiseResultEntity;
import com.dmsdbj.integral.training.entity.ext.ScoreDetailedModel;
import com.dmsdbj.integral.training.entity.ext.ScoreModel;
import com.dmsdbj.integral.training.provider.dao.train.AppraiseResultDao;
import com.dmsdbj.integral.training.provider.jpa.AppraiseResultJpa;
import com.dmsdbj.integral.training.provider.service.AppraiseDetailService;
import com.dmsdbj.integral.training.provider.service.AppraiseResultService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * AppraiseResultService接口实现类
 * 评价记录汇总表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("appraiseResultService")
public class AppraiseResultServiceImpl extends BaseServiceImpl<AppraiseResultEntity> implements AppraiseResultService {

	@Resource
    private AppraiseResultDao appraiseResultDao;

    @Resource
    private AppraiseResultJpa appraiseResultJpa;

    @Resource
    private AppraiseDetailService appraiseDetailService;

    @Override
    protected JpaRepository<AppraiseResultEntity, String> getRealJpa() {
        return appraiseResultJpa;
    }


    @Override
    public List<ScoreModel> updateScore(List<ScoreModel> scoreModel) {
        log.info("插入评分详情，包括详情表和总分记录表-{}",scoreModel);
        for(ScoreModel models:scoreModel){
            List<ScoreDetailedModel> scoreDetailedModels=models.getScoreDetailedModels();
            log.info("获取每一条评价详情的具体信息");
            int detailSize=models.getScoreDetailedModels().size();
            log.info("获取的评价详情总共{}条",detailSize);
            //获取评价详情，并存储到AppraiseDetail表中；
            for(int i=0;i<detailSize;i++){
                AppraiseDetailEntity appraiseDetailEntity=new AppraiseDetailEntity();
                BeanUtils.copyProperties(models,appraiseDetailEntity);
                //获取具体每条的得分
                appraiseDetailEntity.setIntegral(scoreDetailedModels.get(i).getIntegral());
                //获取评价指标
                appraiseDetailEntity.setSubTempletId(scoreDetailedModels.get(i).getSubTempletId());
                 //判断是更新还是插入
                if(StringUtils.isEmpty(models.getScoreDetailedModels().get(i).getDetailId())){
                    AppraiseDetailEntity detailEntity=appraiseDetailService.insert(appraiseDetailEntity);
                    //重新给detailId的赋值
                    scoreDetailedModels.get(i).setDetailId(detailEntity.getId());
                }else{
                    appraiseDetailEntity.setId(models.getScoreDetailedModels().get(i).getDetailId());
                    appraiseDetailService.update(appraiseDetailEntity);
                }
            }
            //计算所有评价的总分
           AppraiseResultEntity appraiseResultEntity=new AppraiseResultEntity();
            BeanUtils.copyProperties(models,appraiseResultEntity);
            if(StringUtils.isEmpty(models.getResultId())){
                AppraiseResultEntity resultEntity=this.insert(appraiseResultEntity);
                //重新给ResultID赋值
                models.setResultId(resultEntity.getId());
            }else {
                appraiseResultEntity.setId(models.getResultId());
                log.info("插入或更新评分结果表数据：{}-" + appraiseResultEntity);
                this.update(appraiseResultEntity);
            }
            log.info("插入或更新评分结果数据成功");

        }
        return scoreModel;
    }
}
