package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TrainingProgramJPA接口
 * 培养计划项目表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Repository("trainingProgramJpa")
public interface TrainingProgramJpa extends JpaRepository<TrainingProgramEntity, String> {

}
