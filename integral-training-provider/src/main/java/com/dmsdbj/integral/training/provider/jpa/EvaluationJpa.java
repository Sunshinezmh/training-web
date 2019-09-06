package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EvaluationJPA接口
 * 评价他人表：
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("evaluationJpa")
public interface EvaluationJpa extends JpaRepository<EvaluationEntity, String> {

}
