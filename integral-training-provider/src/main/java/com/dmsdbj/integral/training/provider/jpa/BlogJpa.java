package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BlogJPA接口
 * 博客链接表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("blogJpa")
public interface BlogJpa extends JpaRepository<BlogEntity, String> {

}
