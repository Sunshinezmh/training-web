package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import com.dmsdbj.cloud.tool.business.BaseService;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.ext.DeployModel;
import com.dmsdbj.integral.training.entity.ext.currentProjectModel;
import com.dmsdbj.integral.training.entity.ext.finishTaskModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * TrainingProgramService接口
 * 培养计划项目表
 *
 * @author 李娜
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
public interface TrainingProgramService extends BaseService<TrainingProgramEntity> {

    /**
     * 根据级别id查询该该级别下的项目
     *@author 李娜
     * @param id
     * @return  该级别下的项目
     */
    List<TrainingModel> queryProgramByLevelId(String id);

    /**
     * 根据用户id查询该用户是否通过软考
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-6-1 09:18:56
     */
    IntegralResult querySoftWareByUserId(String userId);

    /**
     * 根据级别id查询该该级别下的项目
     *@author 李娜
     * @param id
     * @return  该级别下的项目
     */
    List<TrainingProgramEntity> queryProgramByLevel(String id);

    /**
     * 查询级别下的所有项目（不分页）
     *@author 王婷婷
     * @return  所有项目
     */
    List<TrainingProgramEntity> queryProgram();

    /**
     * 根据等级id集合查询所有项目
     *@author 李娜
     * @param userId
     * @return
     */
    Map<Integer,List<TrainingModel>> queryPrograms(String userId);

    /**
     * 根据programId查询标准分
     * @param programId
     * @return
     * author 严文文
     * @since 0.0.2 2019-1-20 08:21:55
     */
    Integer  queryIntegralByProgramId(String programId);

    /**
     * 批量删除
     *
     * @param  trainingProgramEntities
     * @return 删除后的结果
     * @author 严文文
     * @since 0.0.2 2019-4-27 19:34:07
     */
    IntegralResult<TrainingProgramEntity> deleteAll( List<TrainingProgramEntity> trainingProgramEntities);
    /**
     * 根据remarks查询培养计划加分时间间隔
     * @param remark
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-29 21:27:19
     */

     DeployModel queryInfoByRemarks(String remark);



    /**
     * 批量更新用户
     * @author 严文文
     * @since 2019-5-7 10:02:22
     */
    IntegralResult saveTrainingModels( List<TrainingModel> trainingModels);


    /**
     * 根据项目名查询培养计划
     * @param programName
     * @return
     * @author:严文文
     * @since；2019-6-1 10:37:07
     */
    TrainingProgramEntity queryByName(String  programName);
    /**
     * 根据用户Id查询用户的项目
     * @param: userId
     * @return
     * @auther:刘兵
     * @since：15:48 2019/6/12
     */
    List<TrainingProgramEntity> queryUserProgramByID(String userId);

    /**
     * 查询用户的项目根据用户名
     * @param: userName
     * @return
     * @auther:刘兵
     * @since：15:48 2019/6/12
     */
    List<currentProjectModel> queryUserProjectByUserName(String userName);
    /**
     *
     * @param: 
     * @return
     * @auther:刘兵
     * @since：15:39 2019/6/12
     */
    TrainingProgramEntity queryIntrouctionByProjectName(String projectName);

    /**
     * 查询用户完成的任务
     * @param: userName
     * @return：f List<finishTaskModel>
     * @auther:刘兵
     * @since：20:35 2019/6/18
     */
    List<finishTaskModel> queryFinishTask(String userName);
}
