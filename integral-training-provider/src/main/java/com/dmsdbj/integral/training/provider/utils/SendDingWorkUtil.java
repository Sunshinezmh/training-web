package com.dmsdbj.integral.training.provider.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SendDingWorkUtil
 * @Auther ZMH
 * @Date 2019/7/30 09:04
 */
@Component
public class SendDingWorkUtil {
    private static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 功能描述:获取accesstoken
     *
     * @param:
     * @return:
     * @auther: 张明慧
     * @since: 1.0.0 2018/12/5 20:22
     */
    public String getAccessToken(String appkey, String appsecret) {
        OapiGettokenResponse response = null;
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setCorpid(appkey);
        request.setCorpsecret(appsecret);
        request.setHttpMethod("GET");
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        String body = response.getBody();
        JSONObject jo = JSONObject.parseObject(body);
        String errcode = jo.getString("errcode");
        String access_token = null;
        if ("0".equals(errcode)) {
            access_token = (String) jo.get("access_token");
            redisTemplate.opsForValue().set("ACCESS_TOKEN", access_token, 7200, TimeUnit.SECONDS);
        }
        return access_token;
    }


    public boolean sendWorkMessage(String appkey, String appsecret, String AGENTID, String toUsers, String realName, String projectName) throws Exception {
        String timeStr = sdfTime.format(new Date()).toString();
        System.out.println(redisTemplate);
        //获取accesstoken
        String accessToken = String.valueOf(redisTemplate.opsForValue().get("ACCESS_TOKEN"));
        if (accessToken=="null" || StringUtils.isEmpty(accessToken)) {
            accessToken = getAccessToken(appkey, appsecret);
        }
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(toUsers);
        request.setAgentId(Long.parseLong(AGENTID));
        request.setToAllUser(false);
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
        msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
        msg.getOa().getHead().setText("亲，您好：");
        msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
        msg.getOa().getBody().setContent("亲，您好,经系统自动检测 ，您徒弟 " + realName + " 的以上项目存在延期情况，请您及时和徒弟沟通哦,辛苦啦！");
        msg.getOa().getBody().setAuthor("天赋吉运 · 技术支持    " + timeStr);
        msg.getOa().getBody().setTitle(projectName);
        msg.setMsgtype("oa");
        request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken);
        if (response.getErrorCode().equals("0")) {
            return true;
        }
        return false;
    }
}
