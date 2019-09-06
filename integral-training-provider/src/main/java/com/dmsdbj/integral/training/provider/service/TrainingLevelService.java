package com.dmsdbj.integral.training.provider.service;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import com.dmsdbj.cloud.tool.business.BaseService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * TrainingLevelService接口
 * 培养计划等级表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-05-24 16:11:28
 */


public interface TrainingLevelService extends BaseService<TrainingLevelEntity> {


    /**
     * 查询18级
     *
     * @author 严文文
     * @since 0.0.1 2019-5-24 16:50:21
     */
    List<TrainingLevelModel>  queryAllLevel();



     /**
      * 根据等级ID或者等级名称查找Level
     ** @param levelOrder 等级id
     * @param name       等级名称
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-24 17:07:37
            */

    IntegralResult<PageInfo<TrainingLevelEntity>> queryByLevelIdOrName(int levelOrder, String name, int pageNum, int pageSize);

    /**
     * 查询所有等级内容
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:04:48
     */

    IntegralResult<PageInfo<TrainingLevelModel>> queryAll(int pageNum, int pageSize);

    /**
     * saveImage-保存图片-严文文
     *
     * @Params: file 文件
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:29:26
     */
    String saveImage(MultipartFile file) throws IOException;



    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
   TrainingLevelModel findByOrder( int levelOrder);



}
