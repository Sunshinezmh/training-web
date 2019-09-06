package com.dmsdbj.integral.training.provider.service.impl;

import com.dmsdbj.integral.training.entity.BlogEntity;
import com.dmsdbj.integral.training.entity.RssEntity;
import com.dmsdbj.integral.training.entity.UserEntity;
import com.dmsdbj.integral.training.provider.dao.train.BlogDao;
import com.dmsdbj.integral.training.provider.dao.train.RssDao;
import com.dmsdbj.integral.training.provider.dao.train.UserDao;
import com.dmsdbj.integral.training.provider.jpa.BlogJpa;
import com.dmsdbj.integral.training.provider.service.BlogService;
import com.dmsdbj.cloud.tool.business.BaseServiceImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * BlogService接口实现类
 * 博客链接表
 *
 * @author 严文文 
 * @version 0.0.2
 * @since 0.0.2 2019-06-05 09:37:42
 */
@Slf4j
@Service("blogService")
public class BlogServiceImpl extends BaseServiceImpl<BlogEntity> implements BlogService {
	
	@Resource
    private RssDao rssDao;
    @Resource
    private UserDao userDao;

    @Resource
    private BlogDao blogDao;

    @Resource
    private BlogJpa blogJpa;

    @Override
    protected JpaRepository<BlogEntity, String> getRealJpa() {
        return blogJpa;
    }

@Override
    public boolean updateAllBlog(){
        //1.查询所有用户
        List<UserEntity> userList = userDao.queryAll();
    if (CollectionUtils.isEmpty(userList)) {
        log.error("用户博客更新失败，查询用户为空！");
        return false;
    }
    //收集没有博客RSS订阅地址的人
    List<UserEntity> userEntities=new ArrayList<>();
    //for循环查询每个人的RSS订阅地址
    for (UserEntity userEntity : userList) {
        //根据userId 查询RSS订阅 地址
        RssEntity rssEntity=rssDao.queryByUserId(userEntity.getId());
        if(rssEntity!=null){
            //如果此人有RSS订阅地址，则解析博客地址
//            this.rss(rssEntity.getRssUrl(),userEntity.getId());
        }else{
            //如果此人没有RSS订阅地址，则记录没有RSS订阅地址的人，并继续循环
            userEntities.add(userEntity);
            log.info("用户{}没有RSS订阅地址 ",userEntity .getId());
            continue;
        }

    }
                  //更新此人博客信息

       this.rss("https://blog.csdn.net/java_zyq/rss/list" );
        return false;
    }

    /**
     * 解析RSS订阅地址
     * @param url
     * @author:严文文
     * @since：2019-6-6 15:28:43
     */
    public  void rss(String url){
        try {

            URL url1=new URL( url);
            URLConnection urlConnection = url1.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            BOMInputStream bomIn=new BOMInputStream( inputStream );
//            boolean b = bomIn.hasBOM();
//            System.out.println("-/-/-/--/-/-/-/-/-/-/-/b:"+b);
            XmlReader reader=new XmlReader( bomIn );
            System.out.println("Rss源的编码格式为：" + reader.getEncoding());
            SyndFeedInput input=new SyndFeedInput(  );
            SyndFeed feed = input.build( reader );
            System.out.println("Title*-*-*-*-*-*-*-**-*-*-*"+feed.getTitle());
            System.out.println("*******************开始********************");
            int i=1;
            //删除此人所有的博客信息
//            boolean result=blogDao.deleteByUserId(userId);
            for (SyndEntry entry:feed.getEntries()){
                int j=i++;
                System.out.println("entry.TitleEx   "+entry.getTitleEx());
                System.out.println("entry.description.value        "+entry.getDescription().getValue());
                System.out.println("entry.Link   "+entry.getLink());
                System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*/entry/*/*/*/*      "+entry);
                System.out.println("*****************"+j+"**********************");

            }
            System.out.println("********************结束*******************");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }

}
