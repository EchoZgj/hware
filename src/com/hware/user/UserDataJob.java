package com.hware.user;

import com.hware.core.domain.Testdata;
import com.hware.core.domain.User;
import com.hware.core.service.ITestDataService;
import com.hware.core.service.IUserService;
import com.hware.tools.CommUtil;
import com.hware.util.HttpClientPostMethod;
import net.sf.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/3.
 */
@Component("userData_Job")
public class UserDataJob {

    @Autowired
    IUserService userService;

    @Autowired
    ITestDataService testDataService;



    protected void execute(){
        String path="http://yy.yingyanghome.com/hware_server/userInfoSave.htm";
        Map map = new HashMap();
        map.put("flag", 0);
        System.out.println(userService);
        List<User> userList = userService.query("from User where flag=:flag and uuid is not null",map,-1,-1);
        for(User user:userList){
            String uuid = user.getUuid();
            Testdata testdata = testDataService.getBy("uuid", uuid);

            if(testdata!=null){
                //FileBody fileBody = new FileBody(new File("/home/sendpix0.jpg"));
                Map map1 = new HashMap();
                map1.put("uuid",uuid);
                map1.put("user", JSONObject.fromObject(user));
                map1.put("testdata",JSONObject.fromObject(testdata));
                map1.put("time", CommUtil.formatTime("yyyy-MM-dd HH:mm:ss",user.getMeasureDate()));
                StringBody stringBody = null;
                ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
                    stringBody = new StringBody(JSONObject.fromObject(map1).toString(), contentType);
                MultipartEntity entity = new MultipartEntity();
                //entity.addPart("img", fileBody);

                entity.addPart("json", stringBody);
                try {
                    String msg = HttpClientPostMethod.post(path, entity);
                    System.out.println(msg);
                    JSONObject jsonObject = JSONObject.fromObject(msg);
                    if (jsonObject.getInt("status")==0){
                        User user1 = userService.getBy("uuid",jsonObject.getString("body"));
                        user1.setFlag(1);
                        userService.update(user1);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
