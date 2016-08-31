package com.hware.util;


import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * httpclient上传文件
 * @author linwei
 *
 */
public class HttpClientPostMethod {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        String url = "http://192.168.1.200:8080/userInfoSave.htm";
        //FileBody fileBody = new FileBody(new File("/home/sendpix0.jpg"));
        StringBody stringBody = new StringBody("1");
        MultipartEntity entity = new MultipartEntity();
        //entity.addPart("img", fileBody);
        entity.addPart("goodsId", stringBody);
        try {
            String msg = post(url, entity);
            System.out.println(msg);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static String post(String url,MultipartEntity entity) throws ClientProtocolException, IOException {
        String msg ="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        HttpResponse response = httpclient.execute(post);
       // System.out.println(response.getStatusLine().getStatusCode());
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){

            HttpEntity entitys = response.getEntity();
            if (entity != null) {
                //System.out.println(entity.getContentLength());
                msg = EntityUtils.toString(entitys);
            }
        }
        /*HttpEntity entitys = response.getEntity();
        if (entity != null) {
            //System.out.println(entity.getContentLength());
            msg = EntityUtils.toString(entitys);
        }
*/
        httpclient.getConnectionManager().shutdown();
        return msg;
    }
}
