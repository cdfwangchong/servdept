/*
 * @Author: your name
 * @Date: 2020-09-15 09:15:37
 * @LastEditTime: 2020-09-15 10:02:35
 * @LastEditors: Please set LastEditors
 * @Description: In UserDto Settings Edit
 * @FilePath: \auth0\Jwt.java
 */
package com.ctg.servdept.pojo.until;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.util.Base64;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ctg.servdept.pojo.until.BusinessInfoConst.*;

public class Jwt {

    private static final String SECRET_KEY = "3d990d2276666dfac04467dBBBfff26d";

    /**
     * 生成token，该方法只在用户登录成功后调用
     * 
     * Map集合，主要存储用户id，token生成时间，token过期时间等
     * @return token字符串
     * @throws KeyLengthException
     * 
     * 
     *   Map<String, Object> payload = new HashMap<String,
     *   Object>(); Date date = new Date();
     *   payload.put("accountid",Accountobj.getAccountid());// 用户id
     *   payload.put("accountname",Accountobj.getAccountname());// 用户名称
     *   payload.put("departmentid",Accountobj.getDepartmentid());// 用户id
     *   payload.put("powerid", Accountobj.getPowerid());//
     *   payload.put("status",Accountobj.getStatus());//
     *   payload.put("worknumber",Accountobj.getWorknumber());//
     *   payload.put("iat", date.getTime());// 生成时间
     *   payload.put("ext", date.getTime() + 1 * 1000 * 60 * 60 * 24);// 过期时间1 小时 单位是毫秒
     */
    public String createToken(Map<String, Object> playLoad) {
        JSONObject userInfo = new JSONObject(playLoad);
        Payload payload = new Payload(userInfo);
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWSObject jwsObject = new JWSObject(header, payload);
        JWSSigner signer = null;
        try {
            signer = new MACSigner(SECRET_KEY.getBytes());
        } catch (KeyLengthException e1) {
            e1.printStackTrace();
        }
        try {
            jwsObject.sign(signer);
        } catch (JOSEException e) {
            System.err.println("签名失败" + e.getMessage());
            e.printStackTrace();
        }
        return jwsObject.serialize();
    }

    /**
     * 验证token是否有效
     * 
     * @param token
     * @return
     */
    public Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String[] tokenArr = token.split("\\.");
        Payload payload = new Payload(new Base64(tokenArr[1]).decodeToString());
        boolean verifiedSignature = false;
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
            verifiedSignature = jwsObject.verify(verifier);
        } catch (Exception e) {
            resultMap.put("isSuccess", false);
            resultMap.put("status", TockenExceptiont);
            resultMap.put("Msg", TockenExceptiontMsg);
        }
        if (verifiedSignature) {
            JSONObject jsonObject = payload.toJSONObject();
            Long expTime = Long.valueOf(jsonObject.get("ext").toString());
            Long currTime = new Date().getTime();// System.currentTimeMillis();
            if (expTime < currTime) {
                /**
                 * 判断时间是否失效
                 */
                resultMap.put("isSuccess", false);
                resultMap.put("status", TockenExpire);
                resultMap.put("Msg", TockenExpireMsg);
            } else {
                resultMap.put("isSuccess", true);
                resultMap.put("status", TockenSucess);
                resultMap.put("Msg", TockenSucessMsg);
            }
        } else {
            resultMap.put("isSuccess", false);
            resultMap.put("status", TockenFail);
            resultMap.put("Msg", TockenFailMsg);
        }
        return resultMap;
    }
}