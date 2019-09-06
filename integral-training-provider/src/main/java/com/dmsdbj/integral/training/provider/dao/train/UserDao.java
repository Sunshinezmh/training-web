package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.training.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao接口
 * 积分用户表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 14:31:35
 */
@Mapper
@Repository("userDao")
public interface UserDao {

     List<UserEntity> queryAll();
}
