package com.dmsdbj.integral.training.provider.dao.train;

import com.dmsdbj.integral.training.entity.AppraiseTempletEntity;
import com.dmsdbj.integral.training.entity.ext.FindTempletByMoreIdModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseDetailModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

/**
 * AppraiseTempletDao接口
 * 模板-评价分类-评价指标树
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Mapper
@Repository("appraiseTempletDao")
public interface AppraiseTempletDao {

    /**
     * 根据评价人ID、被评价人ID、项目ID、模板ID查询所有的摸板内容
     * @auther 王婷婷
     * @since 2019年6月7日16:23:35
     *
     */
    List<TempletAppraiseDetailModel>queryTempletContent(FindTempletByMoreIdModel findTempletByMoreIdModel);

    /**
     * 查询所有的父模板
     * @author 王婷婷
     * @since 2019年6月9日15:44:50
     */
    List<AppraiseTempletEntity> queryParentTemplet();

    /**
     * 根据父模板Id查找一、二级模板
     * @param templetId 父模板ID
     * @return 符合条件的一、二级模板
     * @author 王婷婷
     * @since 2019年6月9日16:54:05
     */
    List<TempletAppraiseDetailModel>queryTemplateByTempalteId(@Param("templetId") String templetId);

    /**
     * 根据摸板ID更新摸板名称
     * @param Id
     * @param name
     * @author 王婷婷
     * @since 2019年6月11日10:39:49
     */
    void updateNameById(@Param("Id") String Id,@Param("name")String name);
    /**
     * 删除一级模板
     * @author  王婷婷
     * @since 2019年6月11日15:48:50
     */
    void deleteTemplateById(@Param("templetId")String templetId);
}
