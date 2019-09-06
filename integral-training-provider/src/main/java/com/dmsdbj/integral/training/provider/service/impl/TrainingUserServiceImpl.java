package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.json.JsonUtil;
import com.dmsdbj.integral.kernel.api.facade.IntegralClientService;
import com.dmsdbj.integral.kernel.api.facade.LevelClientService;
import com.dmsdbj.integral.kernel.api.model.IntegralModel;
import com.dmsdbj.integral.kernel.api.model.LevelModel;
import com.dmsdbj.integral.training.api.TrainingConstant;
import com.dmsdbj.integral.training.entity.TrainingUserEntity;
import com.dmsdbj.integral.training.entity.ext.IntegralProgramModel;
import com.dmsdbj.integral.training.api.model.TrainingModel;
import com.dmsdbj.integral.training.provider.dao.train.TrainingUserDao;
import com.dmsdbj.integral.training.provider.jpa.TrainingUserJpa;
import com.dmsdbj.integral.training.provider.service.TrainingProgramService;
import com.dmsdbj.integral.training.provider.service.TrainingUserService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.dmsdbj.cloud.tool.uuid.IdWorker;

import javax.transaction.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * TrainingUserService接口实现类
 * 人员与培养计划关系表
 *
 * @author 智雪艳 
 * @version 0.0.2
 * @since 0.0.2 2018-09-18 11:04:05
 */
@Slf4j
@Service("trainingUserService")
public class TrainingUserServiceImpl extends BaseServiceImpl<TrainingUserEntity> implements TrainingUserService {
    @Resource
    private TrainingProgramService trainingProgramService;

	@Resource
    private TrainingUserDao trainingUserDao;

	@Resource
    private IntegralClientService integralClientService;


	@Resource
    private LevelClientService levelClientService;

    @Resource
    private TrainingUserJpa trainingUserJpa;

    @Override
    protected JpaRepository<TrainingUserEntity, String> getRealJpa() {

        return trainingUserJpa;
    }

    /**
     * 根据用户id 查询进行中的项目
     * @param userId
     * @return
     */
    @Override
    public IntegralResult queryByUserId(String userId) {
        // 根据用户userId查询用户等级
        IntegralResult<LevelModel> integralResult = levelClientService.findLevelById(userId);
        if (integralResult.getCode().equals(IntegralResult.SUCCESS)) {
            if (integralResult.getData() != null && integralResult.getData() != "") {
                String integral = JsonUtil.beanToJson(integralResult.getData(), true);
                LevelModel levelModel = JsonUtil.jsonToBean(integral, LevelModel.class);
                if (levelModel != null) {
                    String levelId = levelModel.getLevelId();
                    //根据等级查询该用户进行中的项目
                    List<TrainingModel> trainingProList = trainingUserDao.queryProgramByLevelAndUser(levelId, userId);
                    //用户没有完成任何一个在此等级的项目,则把该等级的所有项目加载出来
                    if (CollectionUtils.isEmpty(trainingProList)) {
                        List<TrainingModel> trainingModels = trainingProgramService.queryProgramByLevelId(levelId);
                        return IntegralResult.build(IntegralResult.SUCCESS, "根据等级查询项目成功", trainingModels);
                    }
                    return IntegralResult.build(IntegralResult.SUCCESS, "根据等级查询项目成功", trainingProList);
                } else {
                    log.error("根据等级查询项目失败");
                    return IntegralResult.build(IntegralResult.FAIL, "根据等级查询项目失败");
                }
            }
        }
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功");
    }

    /**
     * 根据用户id查询该用户进行中的项目
     * @param userId
     * @return
     * @author 严文文
     * @since 2019-3-26 17:13:33
     */
    @Override
    public IntegralResult queryProgramNameByUserId(String userId){
        //根据userId查询用户该等级下的项目
        IntegralResult trainingProList= this.queryByUserId(userId);
        if (trainingProList.getCode().equals(IntegralResult.SUCCESS)){
            if ( trainingProList.getData() != null &&  trainingProList.getData() != ""){
                List<TrainingModel> models =(List<TrainingModel>)trainingProList.getData();
                if(models.size()>0){
                    int currentLevelOrder =0;
                    for(TrainingModel trainingModel:models){
                        currentLevelOrder=trainingModel.getLevelOrder();
                        //根据is_finished判断用户当前进行的项目
                        Integer isFinished=trainingModel.getIsFinished();
                        String type=trainingProgramService.findById(trainingModel.getId()).getType();
                        //isFinished为1，表示验收通过
                        if( isFinished==null || isFinished==0 || isFinished==2){
                            //判断是否为计算机的项目
                            String programName=trainingProgramService.findById(trainingModel.getId()).getName();
                            if( type.equals("计算机") && programName!=null && !programName.equals("")){
                                return IntegralResult.build(IntegralResult.SUCCESS, "查询项目成功", programName);
                            }else{
                                log.warn("当前用户所在项目的项目名为空字符，请确认！userId={}",userId);
                            }
                        }
                    }
                    //此等级所有计算机项目的isFinished为1，则返回下一等级的第一个计算机项目
                    Integer nextLevel=currentLevelOrder+1;
                    IntegralResult<LevelModel> levelModelIntegralResult=  levelClientService.queryAllLevel();
                    String jsons=JsonUtil.beanToJson(levelModelIntegralResult.getData());
                    List<LevelModel> levelModels=JsonUtil.jsonToList(jsons,LevelModel.class);
                    Optional<LevelModel> levelModel=levelModels.stream().filter(n->n.getLevelOrderId()==(nextLevel)).findAny();
                    String getLevelId=levelModel.get().getId();
                    List<TrainingModel> trainingModels=trainingProgramService.queryProgramByLevelId(getLevelId);
                    Optional<TrainingModel> trainingModelOptional=trainingModels.stream().filter(n->n.getType().equals("计算机")).findFirst();
                    String programName=trainingModelOptional.get().getName();
                    return IntegralResult.build(IntegralResult.SUCCESS, "查询项目成功", programName);

                }else {
                    log.warn("当前用户没有项目信息 userId{},请确认！",userId);
                    return IntegralResult.build(IntegralResult.FAIL, "当前用户没有项目信息");
                }
            }else{
                log.warn("当前用户没有项目信息 userId{},请确认！",userId);
                return IntegralResult.build(IntegralResult.FAIL, "当前用户没有项目信息");
            }
        }
        return IntegralResult.build(IntegralResult.SUCCESS, "查询项目成功");
    }



    //项目完成,加分
    @Override
    @Transactional(rollbackOn=Exception.class)
    public IntegralResult addIntegral(IntegralProgramModel integralProgramModel)
    {
            //调用加分接口
            IntegralModel integralModel=new IntegralModel();
            integralModel.setIdInfo(integralProgramModel.getIdInfo());
            integralModel.setIntegral(integralProgramModel.getIntegral());
            integralModel.setTypeKey(integralProgramModel.getTypeKey());
            integralModel.setGivingUserId(TrainingConstant.GIVING_USER_ID_TRAINING);
            integralModel.setPluginId(integralProgramModel.getPluginId());
            integralModel.setPrimaryId(integralProgramModel.getProgramId());
            integralModel.setTypeKey(integralProgramModel.getTypeKey());
            integralModel.setReason(integralProgramModel.getReason());
            IntegralResult addFinished=integralClientService.addIntegral(integralModel);
            //如果调用加分失败
            if(addFinished.getCode()==IntegralResult.FAIL){
                log.error("培养计划调用加分失败");
                return IntegralResult.build(IntegralResult.FAIL,"培养计划调用加分失败");
            }
            //更新项目为已经完成
        TrainingUserEntity trainingUserEntity=new TrainingUserEntity();
        //根据userId和programId查询信息
        List<TrainingUserEntity> trainingUserEntityList=this.findTrainingUser(integralProgramModel.getUserId(),integralProgramModel.getProgramId());
        if(0 == trainingUserEntityList.size()){
            trainingUserEntity.setId(IdWorker.nextStringId());
        }else{
            trainingUserEntity.setId(trainingUserEntityList.get(0).getId());
        }

        //根据programId查询标准分
        Integer score= trainingProgramService.queryIntegralByProgramId(integralProgramModel.getProgramId());
            if(score > integralProgramModel.getIntegral())
            {
                trainingUserEntity.setIsFinished(2);
            }else {
                trainingUserEntity.setIsFinished(1);
            }

            trainingUserEntity.setProgramId(integralProgramModel.getProgramId());
            trainingUserEntity.setUserId(integralProgramModel.getUserId());
            trainingUserEntity.setCreator(integralProgramModel.getLoginUser());
            trainingUserEntity.setOperator(integralProgramModel.getLoginUser());
            this.update(trainingUserEntity);
            return IntegralResult.build(IntegralResult.SUCCESS,"项目完成更新成功");
    }

    /**
     * 假删除用户id查询该用户所完成的项目
     * @param userId String
     * @author 连迎迎
     * @since 2019年1月6日15:54:38
     */
    @Override
    public void deleteAllByUserId(String userId) {
        List<TrainingUserEntity> trainingUserEntityList= trainingUserDao.selectExitByUserId(userId);
        if (trainingUserEntityList.size()>0){
            trainingUserDao.deleteAllByUserId(userId);
        }
    }

    /**
     * 更新training_user表中字段is_finished为1
     *
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    @Override
    public boolean updateTrainingUser(String userId,String programId,int isFinished){
        boolean result=false;
        List<TrainingUserEntity> trainingUserEntityList=this.findTrainingUser(userId,programId);
        if(trainingUserEntityList.get(0)!=null){
            trainingUserEntityList.get(0).setIsFinished(isFinished);
            TrainingUserEntity trainingUserEntity=this.update(trainingUserEntityList.get(0));
            if(trainingUserEntity!=null){result= true;}else{result= false;}
        }
        return result;

    }

    /**
     * 根据userId和programId查询信息
     *
     * @param userId
     * @param programId
     * @return
     * @author 严文文
     * @since 0.0.2 2019-1-12 10:57:56
     */
    @Override
    public List<TrainingUserEntity>  findTrainingUser(String userId, String programId){
        return trainingUserDao.findTrainingUser( userId,  programId);
    }


}
