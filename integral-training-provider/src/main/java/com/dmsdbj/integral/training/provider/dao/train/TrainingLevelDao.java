package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TrainingLevelDao接口
 * 培养计划等级表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-05-24 16:11:28
 */
@Mapper
@Repository("trainingLevelDao")
public interface TrainingLevelDao {


    List<TrainingLevelModel> queryAllLevel();

    /**
     * 根据等级ID或者等级名称查找Level
     *
     * @param levelOrder 等级id
     * @param name       等级名称
     * @author 严文文
     * @since 0.0.1 2018年8月8日14:56:11
     */
    List<TrainingLevelEntity> queryByLevelIdOrName(@Param("levelOrder") int levelOrder, @Param("name") String name);

    /**
     * 查询所有等级内容
     *
     * @author 严文文
     * @since 0.0.1 2018年8月8日14:55:50
     */
    List<TrainingLevelModel> queryAll();



    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    TrainingLevelModel findByOrder( int levelOrder);

}
