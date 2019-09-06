package com.dmsdbj.integral.training.provider.jpa;

import com.dmsdbj.integral.training.entity.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SnapshotJPA接口
 * 培养计划英语快照表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Repository("snapshotJpa")
public interface SnapshotJpa extends JpaRepository<SnapshotEntity, String> {

}
