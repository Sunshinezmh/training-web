package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.AppraiseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AppraiseDetailJPA接口
 * 评价记录详细表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("appraiseDetailJpa")
public interface AppraiseDetailJpa extends JpaRepository<AppraiseDetailEntity, String> {

}
