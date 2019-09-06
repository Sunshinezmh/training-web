package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.kernel.api.model.LevelModel;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * TrainingUserDao接口
 * 人员与培养计划关系表
 *
 * @author 李娜
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Mapper
@Repository("trainingUserDao")
public interface TrainingUserDao {
    /**
     * 根据级别id和用户id查询该该级别下的项目
     *
     * @param levelId,userId
     * @return  该级别下的项目
     */
    List<TrainingModel> queryProgramByLevelAndUser(@Param("levelId") String levelId, @Param("userlId") String userlId);

    void deleteAllByUserId(String userId);

    List<TrainingUserEntity> selectExitByUserId(String userId);

    List<HashMap> findUser();

    /**
     * 根据用户ID和项目ID查询是否有未通过的记录
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-10 13:22:17
     */
     List<TrainingUserEntity>  findTrainingUser(@Param("userId")String userId, @Param("programId")String programId);


    List<LevelModel> queryLevelByUserId( String userId);

}
