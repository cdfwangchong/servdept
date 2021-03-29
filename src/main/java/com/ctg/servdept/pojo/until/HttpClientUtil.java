package com.ctg.servdept.pojo.until;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HttpClientUtil {

    Logger logger = Logger.getLogger(HttpClientUtil.class);

    public JSONObject doPost(TableInfo all){
        String url="http://10.190.3.4:7100/rxp/buyer/check";//测试地址
//        String url="http://10.190.2.18:7100/rxp/buyer/check";//测试地址

        HttpPost post = new HttpPost(url);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse res;
        JSONObject jsonObject = null;

        //提取报文三个参数，方便用于日志报错显示
        String idcardType = null;
        String idcardNo = null;
        String buyerName = null;
        try {
            idcardType = all.getIdcardType();
            idcardNo = all.getIdcardNo();
            buyerName = all.getBuyerName();
            //开始组装报文,第一层json
            JSONObject json = new JSONObject();
            //将jsonlist放到第一层的json中
            json.put("idcardType", all.getIdcardType());
            json.put("idcardNo", all.getIdcardNo());
            json.put("buyerName", all.getBuyerName());

            //报文组装完成，开始发送
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType

            post.setEntity(s);
            res = httpclient.execute(post);

            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());
                jsonObject = JSONObject.parseObject(result);//返回json格式
                logger.info(idcardType+"#"+idcardNo+"#"+buyerName+"调用海关接口验证岛民身份成功");
                return jsonObject;
            }else{
                String err = EntityUtils.toString(res.getEntity());
                logger.error("拉取失败,错误编码为："+res.getStatusLine().getStatusCode());
                logger.info(idcardType+"#"+idcardNo+"#"+buyerName+"调用海关接口验证岛民身份成功");
                jsonObject = JSONObject.parseObject(err);//返回json格式
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("json报文发送失败"+"idcardType:"+idcardType+"idcardNo"+idcardNo+"buyerName"+buyerName);
            return jsonObject;
        }finally {
            if (post != null) {
                post.releaseConnection();
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error("httpclient资源关闭失败");
                }
            }
        }
    }
}
