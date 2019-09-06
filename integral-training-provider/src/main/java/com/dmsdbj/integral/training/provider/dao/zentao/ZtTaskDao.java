package com.dmsdbj.integral.training.provider.dao.zentao;

import com.dmsdbj.integral.training.entity.ext.DelayTaskModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname ZtTaskDao
 * @Auther ZMH
 * @Date 2019/8/3 10:50
 */
@Mapper
@Repository("ztTaskDao")
@Component
public interface ZtTaskDao {
    /**
     * 功能描述 查询所有延期的人员
     * @return
     * @auther ZMH
     * @date 2019/7/28 11:27
     */
    List<DelayTaskModel> queryAllDelayTask();
}
