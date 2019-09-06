package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.entity.AppraiseTempletEntity;
import com.dmsdbj.cloud.tool.business.BaseService;
import com.dmsdbj.integral.training.entity.ext.FindTempletByMoreIdModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseDetailModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseModel;

import java.util.List;

/**
 * AppraiseTempletService接口
 * 模板-评价分类-评价指标树
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
public interface AppraiseTempletService extends BaseService<AppraiseTempletEntity> {

    /**
     * 根据评价人ID、被评价人ID、项目ID、模板ID查询所有的摸板内容
     * @auther 王婷婷
     * @since 2019年6月7日16:23:35
     *
     */
    List<TempletAppraiseDetailModel> queryTempletContent (FindTempletByMoreIdModel findTempletByMoreIdModel);

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
    List<TempletAppraiseModel> queryTemplateByTempalteId(String templetId);

    /**
     * 根据摸板ID更新摸板名称
     * @param Id
     * @param name
     * @author 王婷婷
     * @since 2019年6月11日10:39:49
     */
    void  updateNameById(String Id,String name);
    /**
     * 添加新的模板
     * @author 王婷婷
     * @since 2019年6月11日10:39:49
     */
    AppraiseTempletEntity addTemplate(AppraiseTempletEntity appraiseTempletEntity);

    /**
     * 删除一级模板
     * @author  王婷婷
     * @since 2019年6月11日15:48:50
     */
   void deleteTemplateById(String templetId);
}
