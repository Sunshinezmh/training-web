package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.UserEntity;
import com.dmsdbj.integral.training.provider.dao.train.UserDao;
import com.dmsdbj.integral.training.provider.jpa.UserJpa;
import com.dmsdbj.integral.training.provider.service.UserService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserService接口实现类
 * 积分用户表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-06 14:31:35
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService {
	
	@Resource
    private UserDao userDao;

    @Resource
    private UserJpa userJpa;

    @Override
    protected JpaRepository<UserEntity, String> getRealJpa() {
        return userJpa;
    }


}
