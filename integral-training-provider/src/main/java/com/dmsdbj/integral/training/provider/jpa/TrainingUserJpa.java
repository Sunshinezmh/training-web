package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.TrainingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TrainingUserJPA接口
 * 人员与培养计划关系表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Repository("trainingUserJpa")
public interface TrainingUserJpa extends JpaRepository<TrainingUserEntity, String> {

}
