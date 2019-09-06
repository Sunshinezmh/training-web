package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import com.dmsdbj.integral.training.entity.ext.DeployModel;
import com.dmsdbj.integral.training.entity.ext.currentProjectModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.dmsdbj.integral.training.entity.ext.finishTaskModel;

import java.util.List;

/**
 * TrainingProgramDao接口
 * 培养计划项目表
 *
 * @author 李娜
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Mapper
@Repository("trainingProgramDao")
public interface TrainingProgramDao {
    /**
     * 根据级别id查询该该级别下的项目
     * @param id
     * @return  TrainingModel
     */
    List<TrainingModel> queryProgramByLevelId(String id);

    /**
     * 根据级别id查询该该级别下的项目
     * @param id
     * @return  TrainingProgramEntity
     */
    List<TrainingProgramEntity> queryProgramByLevel(String id);
    /**
     * 查询该项目
     *
     * @return  TrainingProgramEntity
     */
    List<TrainingProgramEntity> queryProgram();

    /**
     * 查询培养计划所有等级及项目
     * @return
     */
    List<TrainingModel> queryAllProgram();

    List<TrainingModel> queryProgramByuser(String userId);

    /**
     * 根据remarks查询培养计划加分时间间隔
     * @param remark
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-29 21:27:19
     */
     DeployModel queryInfoByRemarks(String remark);


    /**
     * 根据项目名查询培养计划
     * @param programName
     * @return
     * @author:严文文
     * @since；2019-6-1 10:37:07
     */
    TrainingProgramEntity queryByName(String programName);

    /**
     * 查询用户当前等级的中所有项目
     * @param
     * @return
     * @auther:刘兵
     * @since：19:48 2019/6/11
     */
    List<TrainingProgramEntity> queryUserProgramByID(String userId);

    /**
     * 查询用户当前等级，自己进行的项目
     * @param
     * @return
     * @auther:刘兵
     * @since：19:48 2019/6/11
     */
    List<currentProjectModel> queryUserProjectByUserName(String userName);

    /**
     *
     * @param：projectName
     * @return：TrainingProgramEntity
     * @auther:刘兵
     * @since：15:34 2019/6/12
     */
    TrainingProgramEntity queryIntroductionByProjectName(String projectName);

    /**
     * 查询用户完成的任务
     * @param:userName
     * @return：List<finishTaskModel>
     * @auther:刘兵
     * @since：20:13 2019/6/18
     */
    List<finishTaskModel> queryFinishTask(String userName);
}
