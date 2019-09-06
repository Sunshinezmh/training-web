package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TrainingLevelJPA接口
 * 培养计划等级表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-05-24 16:11:28
 */
@Repository("trainingLevelJpa")
public interface TrainingLevelJpa extends JpaRepository<TrainingLevelEntity, String> {

}
