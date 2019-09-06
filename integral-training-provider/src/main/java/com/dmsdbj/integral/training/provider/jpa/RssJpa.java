package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.RssEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RssJPA接口
 * RSS订阅地址表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 15:10:16
 */
@Repository("rssJpa")
public interface RssJpa extends JpaRepository<RssEntity, String> {

}
