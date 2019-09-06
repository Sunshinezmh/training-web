package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserJPA接口
 * 积分用户表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 14:31:35
 */
@Repository("userJpa")
public interface UserJpa extends JpaRepository<UserEntity, String> {

}
