package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.SnapshotEntity;
import com.dmsdbj.integral.training.provider.dao.train.SnapshotDao;
import com.dmsdbj.integral.training.provider.jpa.SnapshotJpa;
import com.dmsdbj.integral.training.provider.service.SnapshotService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SnapshotService接口实现类
 * 培养计划英语快照表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("snapshotService")
public class SnapshotServiceImpl extends BaseServiceImpl<SnapshotEntity> implements SnapshotService {
	
	@Resource
    private SnapshotDao snapshotDao;

    @Resource
    private SnapshotJpa snapshotJpa;

    @Override
    protected JpaRepository<SnapshotEntity, String> getRealJpa() {
        return snapshotJpa;
    }


}
