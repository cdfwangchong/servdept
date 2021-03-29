package com.ctg.servdept.pojo.until;

import com.alibaba.fastjson.JSONObject;
import com.ctg.servdept.pojo.dto.CustTripInfo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GetCustTripInfo {

    Logger logger = Logger.getLogger(GetCustTripInfo.class);

    public JSONObject doPost(CustTripInfo all){
        String url="http://10.191.2.17:8885/api/flight/queryTravel";//测试地址

        HttpPost post = new HttpPost(url);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse res;
        JSONObject jsonObject = null;

        //提取报文三个参数，方便用于日志报错显示
        String saleDate = null;
        String fltDate = null;
        String fltNumber = null;
        String pasName = null;
        String pasNipp = null;
        try {
            fltDate = all.getFltDate();
            fltNumber = all.getFltNumber();
            saleDate = all.getSaleDate();
            pasName = all.getPasName();
            pasNipp = all.getPasNipp();
            //开始组装报文,第一层json
            JSONObject json = new JSONObject();
            //将jsonlist放到第一层的json中
            json.put("saleDate", all.getSaleDate());
            json.put("fltDate", all.getFltDate());
            json.put("fltNumber", all.getFltNumber());
            json.put("pasName", all.getPasName());
            json.put("pasNipp", all.getPasNipp());

            //报文组装完成，开始发送
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType

            post.setEntity(s);
            res = httpclient.execute(post);

            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());
                jsonObject = JSONObject.parseObject(result);//返回json格式
                logger.info(saleDate+pasName+"#"+fltNumber+"#"+fltDate+pasNipp+"调用中行信接口查询旅客行程记录成功");
                return jsonObject;
            }else{
                String err = EntityUtils.toString(res.getEntity());
                logger.error("拉取失败,错误编码为："+res.getStatusLine().getStatusCode());
                logger.info(saleDate+pasName+"#"+fltNumber+"#"+fltDate+pasNipp+"调用中行信接口查询旅客行程记录失败");
                jsonObject = JSONObject.parseObject(err);//返回json格式
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("json报文发送失败"+"pasName:"+pasName+"fltNumber"+fltNumber+"fltDate"+fltDate+"pasNipp"+pasNipp);
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
