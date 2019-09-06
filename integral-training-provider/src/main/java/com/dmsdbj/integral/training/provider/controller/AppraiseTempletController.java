package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.integral.training.entity.AppraiseTempletEntity;
import com.dmsdbj.integral.training.entity.ext.FindTempletByMoreIdModel;
import com.dmsdbj.integral.training.entity.ext.TempletAppraiseModel;
import com.dmsdbj.integral.training.provider.service.AppraiseTempletService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AppraiseTempletController接口
 * 模板-评价分类-评价指标树
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Api(tags = {"模板-评价分类-评价指标树接口"})
@RequestMapping(value = "/appraiseTemplet")
@Slf4j
@RestController
public class AppraiseTempletController {

	@Resource
    private AppraiseTempletService appraiseTempletService;

	/**
     * 添加
     *
     * @param entity AppraiseTemplet
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public AppraiseTempletEntity create(@RequestBody AppraiseTempletEntity entity) {
        return appraiseTempletService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        appraiseTempletService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity AppraiseTemplet
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public AppraiseTempletEntity update(@RequestBody AppraiseTempletEntity entity) {
        return appraiseTempletService.update(entity);
    }

    /**
     * 根据id查找AppraiseTemplet
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-06-05 09:37:42
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<AppraiseTempletEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		AppraiseTempletEntity appraiseTempletEntity = appraiseTempletService.findById(id);
		return IntegralResult.build("0000", "查询成功", appraiseTempletEntity);
    }

    /**
     * 根据评价人ID、被评价人ID、项目ID、模板ID查询所有的摸板内容
     * @auther 王婷婷
     * @since 2019年6月7日16:23:35
     *
     */
  @ApiOperation(value = "根据评价人ID、被评价人ID、项目ID、模板ID查询所有的摸板内容",notes="请输入信息")
    @PostMapping(value = "/queryTempletContent")
    public  IntegralResult queryTempletContent(@RequestBody FindTempletByMoreIdModel findTempletByMoreIdModel){
      log.info("根据评价人ID、被评价人ID、项目ID、模板ID查询所有的摸板内容:"+findTempletByMoreIdModel);
      if(StringUtils.isEmpty(findTempletByMoreIdModel.getUserId())){
          log.warn("用户id不能为空：" + findTempletByMoreIdModel);
          return IntegralResult.build(IntegralResult.FAIL,"查询失败");
      }
      if (StringUtils.isEmpty(findTempletByMoreIdModel.getAppraiserId())){
          log.warn("被评价人id不能为空：" + findTempletByMoreIdModel);
          return IntegralResult.build(IntegralResult.FAIL, "查询失败");
      }
      if(StringUtils.isEmpty(findTempletByMoreIdModel.getProgramId())){
          log.warn("项目ID不能为空："+findTempletByMoreIdModel);
      }
      if(StringUtils.isEmpty(findTempletByMoreIdModel.getProgramType())){
          log.warn("项目类型不能为空：" + findTempletByMoreIdModel);
          return IntegralResult.build(IntegralResult.FAIL, "查询失败");
      }
      return IntegralResult.build(IntegralResult.SUCCESS,"查询成功！",appraiseTempletService.queryTempletContent(findTempletByMoreIdModel));
  }

    /**
     * 查询所有的父模板
     * @author 王婷婷
     * @since 2019年6月9日15:44:50
     */
    @ApiOperation(value = "查询所有的父模板")
    @GetMapping(value = {"/queryParentTemplet"})
    public IntegralResult<AppraiseTempletEntity> queryParentTemplet(){
      List<AppraiseTempletEntity> appraiseTempletEntityList=appraiseTempletService.queryParentTemplet();
      if(CollectionUtils.isEmpty(appraiseTempletEntityList)){
          appraiseTempletEntityList=new ArrayList<>();
      }
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功！",appraiseTempletEntityList);
    }


    /**
     * 根据父模板Id查找一、二级模板
     * @param templetId 父模板ID
     * @author 王婷婷
     * @since 2019年6月9日16:54:05
     */
    @ApiOperation(value = "根据父模板Id查找一、二级模板",notes ="请输入父模板ID" )
    @GetMapping(value = {"/queryTemplateByTempalteId/{templetId}"})
    public IntegralResult<TempletAppraiseModel> queryTemplateByTempalteId(@ApiParam( value = "父模板ID",required = true) @PathVariable String templetId){
        List<TempletAppraiseModel> appraiseTempletEntityList=appraiseTempletService.queryTemplateByTempalteId(templetId);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",appraiseTempletEntityList);
    }


    /**
     * 根据摸板ID更新摸板名称
     * @param Id
     * @param name
     * @author 王婷婷
     * @since 2019年6月11日10:39:49
     */
     @ApiOperation(value ="根据摸板ID更新摸板名称",notes = "输入ID和名字")
     @GetMapping(value = {"/updateNameById/{Id}/{name}"})
     public  IntegralResult<String>updateNameById(@ApiParam(value = "摸板ID",required = true)@PathVariable String Id,
                                                  @ApiParam(value = "模板姓名",required=true)@PathVariable String name){
         appraiseTempletService.updateNameById(Id,name);
       return IntegralResult.build(IntegralResult.SUCCESS,"模板更新成功") ;
     }

    /**
     * 添加新的模板
     * @author 王婷婷
     * @since 2019年6月11日10:39:49
     */
    @ApiOperation(value = "添加摸板信息",notes = "添加摸板信息")
    @PostMapping({"/addTemplate"})
   public AppraiseTempletEntity addTemplate(@RequestBody AppraiseTempletEntity appraiseTempletEntity){
       return  appraiseTempletService.addTemplate(appraiseTempletEntity);
   }

    /**
     * 删除一级模板
     * @author  王婷婷
     * @since 2019年6月11日15:48:50
     */
   @ApiOperation(value = "删除摸板",notes = "删除摸板")
    @PostMapping({"deleteTemplateById/{templetId}"})
    public  IntegralResult<String> deleteTemplateById(@ApiParam(value = "templetId")@PathVariable String templetId){
       appraiseTempletService.deleteTemplateById(templetId);
       return IntegralResult.build(IntegralResult.SUCCESS, "删除一级模板成功", "ok");
   }

}    
