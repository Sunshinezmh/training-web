package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.AppraiseTempletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AppraiseTempletJPA接口
 * 模板-评价分类-评价指标树
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("appraiseTempletJpa")
public interface AppraiseTempletJpa extends JpaRepository<AppraiseTempletEntity, String> {

}
