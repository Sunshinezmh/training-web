package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.entity.TrainingUserEntity;
import com.dmsdbj.cloud.tool.business.BaseService;
import com.dmsdbj.integral.training.entity.ext.IntegralProgramModel;
import com.dmsdbj.integral.training.api.model.TrainingModel;

import java.util.List;

/**
 * TrainingUserService接口
 * 人员与培养计划关系表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
public interface TrainingUserService extends BaseService<TrainingUserEntity> {
    /**
     * 根据用户id 查询用户进行中的项目
     * @param userId
     * @return
     */
    IntegralResult<TrainingModel> queryByUserId(String userId);

    /**
     * 根据用户id查询该用户进行中的项目
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-3-26 17:13:33
     */
     IntegralResult queryProgramNameByUserId(String userId);
    /**
     * 项目完成给用户加分,并更细项目为已经完成
     * @param integralProgramModel
     * @return
     */
    IntegralResult addIntegral (IntegralProgramModel integralProgramModel);

    /**
     * 假删除用户id查询该用户所完成的项目
     * @param userId String
     * @author 连迎迎
     * @since 2019年1月6日15:54:38
     */
    void deleteAllByUserId(String userId);

    /**
     * 更新training_user表中字段is_finished为1
     * @param userId
     * @param programId
     * @param isFinished
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    boolean updateTrainingUser(String userId,String programId,int isFinished);

    /**
     * 根据userId和programId查询信息
     *
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    List<TrainingUserEntity>  findTrainingUser(String userId, String programId);

}
