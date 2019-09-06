package com.dmsdbj.integral.training.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.integral.training.entity.ext.DelayTaskModel;
import com.dmsdbj.integral.training.provider.dao.train.TrainingUserDao;
import com.dmsdbj.integral.training.provider.dao.zentao.ZtTaskDao;
import com.dmsdbj.integral.training.provider.service.ZtTaskService;
import com.dmsdbj.integral.training.provider.utils.HttpHelper;
import com.dmsdbj.integral.training.provider.utils.SendDingWorkUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname ZentaoTaskController
 * @Auther ZMH
 * @Date 2019/7/30 00:19
 */
@Slf4j
@Service("ztTaskService")
public class ZtTaskServiceImpl implements ZtTaskService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ZtTaskDao ztTaskDao;

    @Resource
    private TrainingUserDao trainingUserDao;

    /**
     * 应用的唯一标识key
     */
    @Value("${appkey}")
    private String appkey;
    /**
     * 应用的密钥
     */
    @Value("${appsecret}")
    private String appsecret;

    // 企业应用id
    @Value("${AGENTID.value}")
    private String AGENTID;

    //纪老师的钉钉ID
    @Value("${MrJiDingID.value}")
    private String MrJiDingID;

    @Resource
    SendDingWorkUtil sendDingWorkUtil;



    /**
     * 功能描述 查询所有延期的人员
     *
     * @return
     * @auther ZMH
     * @date 2019/7/28 11:27
     */
    @Override
    public void queryAllDelayTask() {
        //1.0 查询所有的用户信息存在map集合中，下面根据用户的姓名查询id的时候需要用到
        Map<String,String> userMap =  getUserIdByName();
        if(userMap == null){
            return;
        }
        //1.1查询所有的延期情况
        List<DelayTaskModel> taskModelList = ztTaskDao.queryAllDelayTask();
        if (taskModelList == null) {
            return;
        }
        //1.2根据用户的姓名把查询到的所有延期项目进行汇总
        Map<String, List<DelayTaskModel>> listMap = taskModelList.stream().collect(Collectors.groupingBy(DelayTaskModel::getRealName, Collectors.toList()));
        if (listMap == null) {
            return;
        }
        for (Map.Entry<String, List<DelayTaskModel>> stringListEntry : listMap.entrySet()) {
            String realName = "";
            StringBuilder projectName = new StringBuilder();
            HashMap<Object, Object> mentorMap = new HashMap();
            if (stringListEntry.getValue().size() <= 0) {
                continue;
            }
            for (DelayTaskModel delayTaskModel : stringListEntry.getValue()) {
                if (delayTaskModel == null) {
                    continue;
                }
                //1.2.1 根据用户名得到用户的ID userID
                delayTaskModel.setUserId(userMap.get(delayTaskModel.getRealName()));
                //1.2.2 得到延期人员的姓名
                realName = delayTaskModel.getRealName();
                //1.2.3 得到延期的天数
                long day = (System.currentTimeMillis() - delayTaskModel.getDeadLine().getTime()) / (1000 * 3600 * 24);
                //1.2.4 得到延期人员的项目名称
                projectName.append(delayTaskModel.getProjectName()).append("----延期天数: ").append(day).append("天").append(",");
                //1.2.5 根据usesrID得到用户师傅
                delayTaskModel.setMentors(getMoterByUserId(delayTaskModel.getUserId()));;
                //1.2.6 得到延期人员的师傅的集合
                if (delayTaskModel.getMentors()!=null) {
                    for (Object o : delayTaskModel.getMentors()) {
                        mentorMap.put(o, o);
                    }
                }
            }

            String toMentorsStr = "";
            //1.3 根据得到的师傅的map集合转成字符串
            List<Object> strList = new ArrayList<Object>();
            if (!mentorMap.isEmpty()) {
                for (Object o : mentorMap.values()) {
                    strList.add(o);
                }
                for (Object o : strList) {
                    String dingId = getDingId(o);
                    if (!StringUtils.isEmpty(dingId)) {
                        toMentorsStr += dingId + "|";
                    }
                }
            }
            //1.4根据拼接的字符截取到师傅集和项目集
            if (!StringUtils.isEmpty(toMentorsStr)) {
                toMentorsStr = toMentorsStr.substring(0, toMentorsStr.length() - 1);
            }
            String projectNameStr="";
            if (!StringUtils.isEmpty(projectName.toString())) {
                projectNameStr = projectName.substring(0, projectName.length() - 1);
            }
            try {
                //1.5发送钉钉消息
                String toUsers = "07615724436689";
                //String toUsers=toMentorsStr+"|"+MrJiDingID;
                if (!StringUtils.isEmpty(toMentorsStr)) {
                    sendDingWorkUtil.sendWorkMessage(appkey, appsecret, AGENTID, toUsers, realName, projectNameStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("钉钉消息发送失败，realName{}:", realName, e);
            }
        }

    }

    /**
     * 根据usesrID得到用户师傅
     * @param userId 用户ID
     * @return
     * @auther ZMH
     * @date 2019/8/4 14:42
     */
    private List getMoterByUserId(String userId) {
        List mentorList = Lists.newArrayList();
        if (!StringUtils.isEmpty(userId)) {
            //调用师徒制服务的接口，查询用户的师傅
            String url = "https://dmsdbj.com/mentorship-web/api/mentorshipGroup/queryMyMasterByUserId/" + userId;
            JSONObject response = null;
                response = HttpHelper.httpGet(url);
                if (response != null) {
                    JSONArray masterResult = JSONArray.parseArray(response.get("data").toString());
                    if (masterResult.size() > 0) {
                        //循环取出师傅ID，并放到集合中
                        for (int i = 0; i < masterResult.size(); i++) {
                            String masterId = masterResult.getJSONObject(i).get("masterId").toString();
                            if (!StringUtils.isEmpty(masterId)) {
                                mentorList.add(masterId);
                            }
                        }
                    }
                }

        }
        return mentorList;
    }

    /**
     * 功能描述 根据userId获取钉钉ID
     *
     * @param userId
     * @return userDingId
     * @auther ZMH
     * @date 2019/8/1 12:51
     */
    public String getDingId(Object userId) {
        //调用钉钉服务的接口，根据userId获取钉钉ID
        String url = "https://dmsdbj.com/dingtalk-web/userDing/findById/" + userId.toString();
        String userDingId = "";
        JSONObject response = HttpHelper.httpGet(url);
            if (response != null) {
                JSONObject data = JSONObject.parseObject(response.get("data").toString());
                if (data != null) {
                    //从返回的数据中取出钉钉ID
                    userDingId = data.get("userDingId").toString();
                }
            }
        return userDingId;
    }

    /**
     * 查询所有的用户信息存在map集合中，下面根据用户的姓名查询id的时候需要用到
     *
     * @param
     * @return
     * @auther ZMH
     * @date 2019/8/4 8:13
     */
    public Map<String, String> getUserIdByName() {
        Map<String, String> userMap = Maps.newHashMap();
        List<HashMap> userLists = trainingUserDao.findUser();
        if (CollectionUtils.isEmpty(userLists)) {
            return null;
        }
        for (HashMap userList : userLists) {
            if (userList != null) {
                userMap.put(String.valueOf(userList.get("user_name")), String.valueOf(userList.get("id")));
            }
        }
        return userMap;
    }
}
