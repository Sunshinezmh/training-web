package com.dmsdbj.integral.training.provider.controller;

import com.dmsdbj.cloud.tool.enums.IntegralResultEnum;
import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import com.dmsdbj.integral.training.provider.service.TrainingLevelService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * TrainingLevelController接口
 * 培养计划等级表
 * 
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-05-24 16:11:28
 */
@Api(tags = {"培养计划等级表接口"})
@RequestMapping(value = "/trainingLevel")
@RestController
public class TrainingLevelController {

	@Resource
    private TrainingLevelService trainingLevelService;

	/**
     * 添加
     *
     * @param entity TrainingLevel
     * @return 添加的结果
	 * @author 严文文 
     * @since 0.0.2 2019-05-24 16:11:28
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public TrainingLevelEntity create(@RequestBody TrainingLevelEntity entity) {
        return trainingLevelService.insert(entity);
    }

    /**
     * 删除
     *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-05-24 16:11:28
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public void delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        trainingLevelService.deleteById(id);
    }

    /**
     * 修改
     *
     * @param entity TrainingLevel
     * @return 修改后的结果
	 * @author 严文文 
     * @since 0.0.2 2019-05-24 16:11:28
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public TrainingLevelEntity update(@RequestBody TrainingLevelEntity entity) {
        return trainingLevelService.update(entity);
    }

    /**
     * 根据id查找TrainingLevel
	 *
     * @param id 主键id
	 * @author 严文文 
     * @since 0.0.2 2019-05-24 16:11:28
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public IntegralResult<TrainingLevelEntity> findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
		TrainingLevelEntity trainingLevelEntity = trainingLevelService.findById(id);
		return IntegralResult.build("0000", "查询成功", trainingLevelEntity);
    }

    /**
     * 查询18级
     *
     * @author 严文文
     * @since 0.0.1 2019-5-24 16:50:21
     */
    @ApiOperation(value = "查询所有等级")
    @GetMapping(value = {"/queryAllLevel"})
    public IntegralResult<TrainingLevelModel> queryAllLevel() {
        List<TrainingLevelModel> TrainingLevelEntity = trainingLevelService.queryAllLevel();
        return IntegralResult.build(IntegralResultEnum.EXECUTE_SUCCESS, TrainingLevelEntity);

    }

    /**
     * 根据等级ID或者等级名称查找Level
     *
     * @param levelOrder 等级id
     * @param name       等级名称
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-24 17:04:31
     */
    @ApiOperation(value = "根据等级ID或者等级名称查询", notes = "请输入等级id或者等级名称进行查询")
    @GetMapping(value = {"/findByLevelIdOrName/{levelOrder}/{name}/{pageNum}/{pageSize}"})
    public IntegralResult<PageInfo<TrainingLevelEntity>> queryByLevelIdOrName(
            @ApiParam(value = "等级id", required = true, example = "1") @PathVariable int levelOrder,
            @ApiParam(value = "等级名称", required = false) @PathVariable String name,
            @ApiParam(value = "页码", required = true, example = "1") @PathVariable int pageNum,
            @ApiParam(value = "每页数量", required = true, example = "10") @PathVariable int pageSize) {
        return trainingLevelService.queryByLevelIdOrName(levelOrder, name, pageNum, pageSize);
    }

    /**
     * 根据等级ID或者等级名称查找Level
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:03:52
     */
    @ApiOperation(value = "查询所有等级内容", notes = "请输入每页数量和页码")
    @GetMapping(value = {"/queryAll/{pageNum}/{pageSize}"})
    public IntegralResult<PageInfo<TrainingLevelModel>> queryAll(
            @ApiParam(value = "页码", required = true, example = "1") @PathVariable int pageNum,
            @ApiParam(value = "每页数量", required = true, example = "10") @PathVariable int pageSize) {
        return trainingLevelService.queryAll(pageNum, pageSize);
    }

    /**
     * saveImage-保存图片-严文文
     *
     * @Params: file 文件
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:28:36
     */
    @ApiOperation(value = "上传图片")
    @PostMapping(value = {"/upload"})
    public IntegralResult<String> uploadImage(@RequestParam(value="multfile",required=false)MultipartFile file) {
        try {
            String imagePath = trainingLevelService.saveImage(file);
            return IntegralResult.build(IntegralResult.SUCCESS, "文件：" + file.getName() + "上传成功", imagePath);
        } catch (IOException e) {
            return IntegralResult.build(IntegralResult.FAIL, "上传失败", e.getMessage());
        }
    }


    /**
     * pc端上传图片
     * @param file
     * @return
     */
    @ApiOperation(value = "pc端上传图片")
    @PostMapping(value = "/uploadPC")
    public IntegralResult<String> uploadPC(MultipartFile file) {
        try {
            String imagePath = trainingLevelService.saveImage(file);
            return IntegralResult.build(IntegralResult.SUCCESS, "文件：" + file.getName() + "上传成功", imagePath);
        } catch (IOException e) {
            return IntegralResult.build(IntegralResult.FAIL, "上传失败", e.getMessage());
        }
    }


    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    @ApiOperation(value = "查询对应等级的TrainingLevelModel")
    @GetMapping(value = "/findByOrder")
    public IntegralResult<TrainingLevelModel> findByOrder(int levelOrder){
       TrainingLevelModel trainingLevelModels=trainingLevelService.findByOrder( levelOrder);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询对应等级的TrainingLevelModel" , trainingLevelModels);
    }

}    
