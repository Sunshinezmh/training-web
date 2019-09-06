package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.AppraiseTempletEntity;
import com.dmsdbj.integral.training.entity.ext.FindTempletByMoreIdModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseDetailModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseModel;
import com.dmsdbj.integral.training.provider.dao.train.AppraiseTempletDao;
import com.dmsdbj.integral.training.provider.jpa.AppraiseTempletJpa;
import com.dmsdbj.integral.training.provider.service.AppraiseTempletService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * AppraiseTempletService接口实现类
 * 模板-评价分类-评价指标树
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("appraiseTempletService")
public class AppraiseTempletServiceImpl extends BaseServiceImpl<AppraiseTempletEntity> implements AppraiseTempletService {

    @Resource
    private AppraiseTempletDao appraiseTempletDao;

    @Resource
    private AppraiseTempletJpa appraiseTempletJpa;

    @Override
    protected JpaRepository<AppraiseTempletEntity, String> getRealJpa() {
        return appraiseTempletJpa;
    }


    @Override
    public List<TempletAppraiseDetailModel> queryTempletContent(FindTempletByMoreIdModel findTempletByMoreIdModel) {
        return appraiseTempletDao.queryTempletContent(findTempletByMoreIdModel);
    }

    @Override
    public List<AppraiseTempletEntity> queryParentTemplet() {
        return appraiseTempletDao.queryParentTemplet();
    }

    @Override
    public List<TempletAppraiseModel> queryTemplateByTempalteId(String templetId) {
      List<TempletAppraiseDetailModel> appraiseTempletEntityList= appraiseTempletDao.queryTemplateByTempalteId(templetId);
      return  orgnizeTreeByAllTemplet(appraiseTempletEntityList);
    }

    @Override
    public void updateNameById(String Id, String name) {
        appraiseTempletDao.updateNameById(Id,name);
    }

    @Override
    public AppraiseTempletEntity addTemplate(AppraiseTempletEntity appraiseTempletEntity) {
        AppraiseTempletEntity templetEntity=new AppraiseTempletEntity();
        if(appraiseTempletEntity !=null){
            templetEntity= this.insert(appraiseTempletEntity);
        }
        templetEntity.setTempletId(this.findById(templetEntity.getId()).getId());
        this.update(templetEntity);

        return templetEntity;
    }

    @Override
    public void deleteTemplateById(String templetId) {
        appraiseTempletDao.deleteTemplateById(templetId);
    }

    /**
     *将结构生成树形结构
     * @author 王婷婷
     * @since 2019年6月9日17:17:53
     */
    public List<TempletAppraiseModel> orgnizeTreeByAllTemplet(List<TempletAppraiseDetailModel> appraiseTempletEntityList){
      List<TempletAppraiseModel> models=new ArrayList<>();
      if(appraiseTempletEntityList.size()>0){
          Map<String,TempletAppraiseModel> templetAppraiseModelMap=new LinkedHashMap<>();
          for(TempletAppraiseDetailModel template:appraiseTempletEntityList){
              if(template.getType()==0){
                  continue;
              }else if(template.getType()==1){
                  String id=template.getId();
                  TempletAppraiseModel templetAppraiseModel=templetAppraiseModelMap.computeIfAbsent(id,k->new TempletAppraiseModel());
                  BeanUtils.copyProperties(template,templetAppraiseModel);
                  templetAppraiseModelMap.put(id,templetAppraiseModel);
              }else if(template.getType()==2){
                  String parentId=template.getParentId();
                  TempletAppraiseModel templetAppraiseModel=templetAppraiseModelMap.computeIfAbsent(parentId,k->new TempletAppraiseModel());
                  List<TempletAppraiseDetailModel> list=templetAppraiseModel.getTempletAppraiseEntityList();
                  if(list==null){
                      list=new ArrayList<>();
                  }
                  list.add(template);
                  templetAppraiseModel.setTempletAppraiseEntityList(list);
                  templetAppraiseModelMap.put(parentId,templetAppraiseModel);
              }
          }
          for(Map.Entry<String,TempletAppraiseModel>entry :  templetAppraiseModelMap.entrySet()){
              models.add(entry.getValue());
          }
      }
      return  models;
    }




}
