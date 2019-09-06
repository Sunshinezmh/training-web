package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.integral.kernel.api.facade.LevelClientService;
import com.dmsdbj.integral.kernel.api.model.LevelModel;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.entity.TrainingLevelEntity;
import com.dmsdbj.integral.training.entity.TrainingProgramEntity;
import com.dmsdbj.integral.training.entity.ext.DeployModel;
import com.dmsdbj.integral.training.entity.ext.currentProjectModel;
import com.dmsdbj.integral.training.entity.ext.finishTaskModel;
import com.dmsdbj.integral.training.provider.dao.train.TrainingProgramDao;
import com.dmsdbj.integral.training.provider.dao.train.TrainingUserDao;
import com.dmsdbj.integral.training.provider.jpa.TrainingProgramJpa;
import com.dmsdbj.integral.training.provider.service.TrainingLevelService;
import com.dmsdbj.integral.training.provider.service.TrainingProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * TrainingProgramService接口实现类
 * 培养计划项目表
 *
 * @author 智雪艳
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Slf4j
@Service("trainingProgramService")
public class TrainingProgramServiceImpl extends BaseServiceImpl<TrainingProgramEntity> implements TrainingProgramService {

    @Resource
    private TrainingProgramDao trainingProgramDao;


    @Resource
    private TrainingLevelService trainingLevelService;

    @Resource
    private TrainingProgramJpa trainingProgramJpa;

    @Resource
    private TrainingUserDao trainingUserDao;

    @Resource
    private LevelClientService levelClientService;

    @Override
    protected JpaRepository<TrainingProgramEntity, String> getRealJpa() {

        return trainingProgramJpa;
    }

    @Override
    public List<TrainingModel> queryProgramByLevelId(String id) {
        return trainingProgramDao.queryProgramByLevelId(id);
    }

    @Override
    public List<TrainingProgramEntity> queryProgramByLevel(String id) {
        return trainingProgramDao.queryProgramByLevel(id);
    }
    @Override
    public List<TrainingProgramEntity> queryProgram() {
        return trainingProgramDao.queryProgram();
    }
    @Override
    public Map<Integer, List<TrainingModel>> queryPrograms(String userId) {
        Map<Integer, List<TrainingModel>> trainingMap = new HashMap<>();
        //查询所有项目
        List<TrainingModel> trainingLevel = trainingProgramDao.queryProgramByuser(userId);

        for (TrainingModel trainingModel : trainingLevel) {
            if (trainingMap.containsKey(trainingModel.getLevelOrder())) {
                trainingMap.get(trainingModel.getLevelOrder()).add(trainingModel);
            } else {
                List<TrainingModel> trainingModels = new ArrayList<>();
                trainingModels.add(trainingModel);
                trainingMap.put(trainingModel.getLevelOrder(), trainingModels);
            }
        }
        return trainingMap;
    }

    /**
     * 根据programId查询标准分
     *
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-20 08:21:55
     */
    @Override
    public Integer queryIntegralByProgramId(String programId) {

        TrainingProgramEntity trainingProgramEntity = this.findById(programId);
        String trainingIntegral = trainingProgramEntity.getIntegral();
        String[] data = trainingIntegral.split("-");
        Double rate = trainingProgramEntity.getRate();
        Integer Integral = 0;
        //判断分值是区间范围 or 只有一个数字
        if (data.length > 1) {
            int lowIntegral = Integer.parseInt(data[0]);
            int topIntegral = Integer.parseInt(data[1]);
            Integral = (new Double((topIntegral - lowIntegral) * rate)).intValue() + lowIntegral;
        }
        return Integral;
    }

    /**
     * 批量删除
     *
     * @param  trainingProgramEntities
     * @return 删除后的结果
     * @author 严文文
     * @since 0.0.2 2019-4-27 19:34:07
     */
    @Override
    public IntegralResult<TrainingProgramEntity> deleteAll( List<TrainingProgramEntity> trainingProgramEntities){

        for (TrainingProgramEntity trainingProgramEntity:trainingProgramEntities) {
            try {
                this.delete(trainingProgramEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  IntegralResult.build("0000", "批量删除成功");

    }
    /**
     * 根据remarks查询培养计划加分时间间隔
     *
     * @param remark
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-29 21:27:19
     */

    @Override
    public DeployModel queryInfoByRemarks(String remark) {
        return trainingProgramDao.queryInfoByRemarks(remark);
    }


    /**
     * 批量更新用户
     * @author 严文文
     * @since 2019-5-7 10:02:22
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public IntegralResult saveTrainingModels(@RequestBody List<TrainingModel> trainingModels){
        if(trainingModels.size()!=0){
            TrainingProgramEntity trainingProgramEntity =new TrainingProgramEntity();
            for (TrainingModel trainingModel:trainingModels) {
                trainingProgramEntity.setDescription(trainingModel.getDescription());
                trainingProgramEntity.setIntegral(trainingModel.getIntegral());
                trainingProgramEntity.setLevelId(trainingModel.getLevelId());
                trainingProgramEntity.setLevelOrder(trainingModel.getLevelOrder());
                trainingProgramEntity.setName(trainingModel.getName());
                trainingProgramEntity.setRate(Double.parseDouble(trainingModel.getRate()));
                trainingProgramEntity.setType(trainingModel.getType());
                return  IntegralResult.build("0000", "批量删除成功",this.insert(trainingProgramEntity));
            }
        }
        return  IntegralResult.build("0000","批量删除成功");
    }



    /**
     * 根据用户id查询该用户是否通过软考
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-6-1 09:18:56
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public IntegralResult querySoftWareByUserId(String userId) {
        boolean result=false;
        //1.查询软考项目目前所有在等级
        String programName = "软考";
        TrainingProgramEntity trainingProgramEntity = trainingProgramDao.queryByName(programName);
        if(trainingProgramEntity.getId()==null){
            log.info("软考没有等级信息");
            return IntegralResult.build("0000", "软考没有等级信息", false);
        }
        String levelId = trainingProgramEntity.getLevelId();
        TrainingLevelEntity trainingLevelEntity = trainingLevelService.findById(levelId);
        Integer programLevelOrder = trainingLevelEntity.getLevelOrder();
        //2.查询用户目前所在等级
        List<LevelModel> levelModels=trainingUserDao.queryLevelByUserId(userId);

        String userLevelId = levelModels.get(0).getLevelId();
        TrainingLevelEntity trainingLevelEntities = trainingLevelService.findById(userLevelId);
        if(trainingLevelEntities.getId()==null){
            log.info("用户{}没有等级{}信息",userId,userLevelId);
            return IntegralResult.build("0000", "此人没有等级信息", false);
        }
        Integer userLevelOrder = trainingLevelEntities.getLevelOrder();

        //3.比较等级
        //3.1如果用户等级比软考等级大，则此用户已通过软考
        //3.2如果用户等级比软考等级小，则此用户没有通过软考
        //3.3如果用户等级等于软考等级，判断此用户的软考项目有没有验收过
        if (programLevelOrder < userLevelOrder) {
            result=true;
        } else if (programLevelOrder > userLevelOrder) {
            result=false;
        } else if (programLevelOrder.equals(userLevelOrder)) {
            List<TrainingModel> trainingModelList = trainingUserDao.queryProgramByLevelAndUser(userLevelId, userId);
            Optional<TrainingModel> trainingModelOptional = trainingModelList.stream().filter(item -> item.getLevelId().equals(levelId)).findAny();
            Optional<TrainingModel> trainingModelOptional1 = trainingModelOptional.filter(item -> item.getIsFinished().equals("1"));
            if (trainingModelOptional1.isPresent() == true) {
                result=true;
            } else {
                result=false;
            }
        }
        return IntegralResult.build("0000", "true表示通过软考，false表示没有通过软考", result);
    }

    /**
     * 根据项目名查询培养计划
     * @param programName
     * @return
     * @author:严文文
     * @since；2019-6-1 10:37:07
     */
    @Override
    public TrainingProgramEntity queryByName(String  programName){
        TrainingProgramEntity trainingProgramEntity=trainingProgramDao.queryByName(programName);
        return trainingProgramEntity;
    }

    /**
     * 查询用户当前的等级所有项目
     * @param userId
     * @author:刘兵
     * @return
     */
    public List<TrainingProgramEntity> queryUserProgramByID(String userId){
        List<TrainingProgramEntity> trainingProgramEntityList = trainingProgramDao.queryUserProgramByID(userId);
        return trainingProgramEntityList;
    }

    /**
     * 查询用户当前等级的所有项目
     * @param： userName
     * @return
     * @auther:刘兵
     * @since：15:42 2019/6/11
     */
    public List<currentProjectModel> queryUserProjectByUserName(String userName){
        List<currentProjectModel> currentProjectModelList = trainingProgramDao.queryUserProjectByUserName(userName);
        return currentProjectModelList;
    }
    /**
     * 根据项目名称查询项目信息（模糊查询）
     * @param: projectName
     * @return
     * @auther:刘兵
     * @since：15:37 2019/6/12
     */
    public TrainingProgramEntity queryIntrouctionByProjectName(String projectName){
        TrainingProgramEntity trainingProgramEntity = trainingProgramDao.queryIntroductionByProjectName(projectName);
        return  trainingProgramEntity;
    }
    /**
     * 查询用户完成的任务
     * @param:userName
     * @return：List<finishTaskModel>
     * @auther:刘兵
     * @since：20:41 2019/6/18
     */
    public List<finishTaskModel> queryFinishTask(String userName){
        List<finishTaskModel> finishTaskModelList = trainingProgramDao.queryFinishTask(userName);
        return finishTaskModelList;
    }

}

