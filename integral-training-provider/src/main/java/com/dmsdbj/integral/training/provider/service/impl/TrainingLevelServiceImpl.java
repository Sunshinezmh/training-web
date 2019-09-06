package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.training.api.model.TrainingLevelModel;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import com.dmsdbj.integral.training.provider.dao.train.TrainingLevelDao;
import com.dmsdbj.integral.training.provider.jpa.TrainingLevelJpa;
import com.dmsdbj.integral.training.provider.service.TrainingLevelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TrainingLevelService接口实现类
 * 培养计划等级表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-05-24 16:11:28
 */
@Slf4j
@Service("trainingLevelService")
public class TrainingLevelServiceImpl extends BaseServiceImpl<TrainingLevelEntity> implements TrainingLevelService {

	@Resource
    private TrainingLevelDao trainingLevelDao;

    @Resource
    private TrainingLevelJpa trainingLevelJpa;

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private TrackerClient trackerClient;

    @Override
    protected JpaRepository<TrainingLevelEntity, String> getRealJpa() {
        return trainingLevelJpa;
    }

    /**
     * 查询18级
     *
     * @author 严文文
     * @since 0.0.1 2019-5-24 16:50:21
     */
    @Override
    public List<TrainingLevelModel> queryAllLevel(){
        return trainingLevelDao.queryAllLevel();
    }


    /**
     * 根据等级ID或者等级名称查找Level
     ** @param levelOrder 等级id
     * @param name       等级名称
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @author 严文文
     * @since 0.0.1 2019-5-24 17:07:37
     */
    @Override
    public IntegralResult<PageInfo<TrainingLevelEntity>> queryByLevelIdOrName(int levelOrder, String name, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<TrainingLevelEntity> levelEntity = trainingLevelDao.queryByLevelIdOrName(levelOrder, name);
        if (CollectionUtils.isEmpty(levelEntity)) {
            levelEntity = new ArrayList<>();
            return IntegralResult.build(IntegralResult.SUCCESS, "没有符合查询要求的内容", levelEntity);
        } else {
            PageInfo<TrainingLevelEntity> info = new PageInfo<>(levelEntity);
            return IntegralResult.build(IntegralResult.SUCCESS, "返回成功", info);
        }

    }

    /**
     * 查询所有等级内容
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author 严文文
     * @since 0.0.1 2018年8月5日11:16:36
     */
    @Override
    public IntegralResult<PageInfo<TrainingLevelModel>> queryAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TrainingLevelModel> levelEntityList = trainingLevelDao.queryAll();
        if (CollectionUtils.isEmpty(levelEntityList)) {
            levelEntityList = new ArrayList<>();
            return IntegralResult.build(IntegralResult.SUCCESS, "没有符合查询要求的内容", levelEntityList);
        } else {
            PageInfo<TrainingLevelModel> pageInfo = new PageInfo<>(levelEntityList);
            long total = pageInfo.getTotal();
            pageInfo.setTotal(total);
            return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", pageInfo);
        }
    }

    /**
     * saveImage-保存图片-连迎迎
     *
     * @Params: file 文件
     * @author 严文文
     * @since 0.0.1 2019-5-25 15:47:34
     */
    @Override
    public String saveImage(MultipartFile file) throws IOException {
        if (file == null) {
            log.error("file is null");
            throw new IllegalArgumentException("file is null");
        }
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String serverPath = trackerClient.getStoreStorage().getIp();
        String imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
        log.info("图片上传成功，地址：" + imagePath);
        return imagePath;
    }



    /**
     * 查询对应等级的TrainingLevelModel
     *
     * @param levelOrder 等级
     * @return LevelEntity
     * @author 严文文
     * @since 0.0.2 2019-6-13 11:34:46
     */
    @Override
    public TrainingLevelModel findByOrder( int levelOrder){
        return trainingLevelDao.findByOrder(  levelOrder);
    }

}
