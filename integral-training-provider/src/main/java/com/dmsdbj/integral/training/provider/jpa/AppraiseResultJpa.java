package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.AppraiseResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AppraiseResultJPA接口
 * 评价记录汇总表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("appraiseResultJpa")
public interface AppraiseResultJpa extends JpaRepository<AppraiseResultEntity, String> {

}
