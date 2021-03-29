package com.ctg.servdept.pojo.until;

import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64;
import net.minidev.json.JSONObject;

/**
 *  DtoJwt user = new DtoJwt(token);
 */
public class DtoJwt {

    /**
     * 用户id
     */
//    private String accountid;
    /**
     * 用户名称
     */
    private String accountname;
    /**
     * 部门id
     */
    private String departmentid;
    /**
     * 权限id
     */
//    private String powerid;
    /**
     * 状态
     */
    private String status;
    /**
     * 工号
     */
    private String worknumber;
    /**
     * 生成token时间
     */
    private String iat;
    /**
     * 过期时间
     */
    private String ext;
    /**
     * 登录次数
     */
//    private String times;
    /**
     * token
     */
    private String token;

    public DtoJwt(String token) {
        String[] tokenArr = token.split("\\.");
        Payload payload = new Payload(new Base64(tokenArr[1]).decodeToString());
        JSONObject jsonObject = payload.toJSONObject();
//        setAccountid(jsonObject.get("accountid").toString());
        setAccountname(jsonObject.get("accountname").toString());

        try {
            jsonObject.get("departmentid").toString();
        } catch (Exception e) {
            setDepartmentid("");
        }

        setExt(jsonObject.get("ext").toString());
        setIat(jsonObject.get("iat").toString());
//        setPowerid(jsonObject.get("powerid").toString());
        setStatus(jsonObject.get("status").toString());
        setWorknumber(jsonObject.get("worknumber").toString());
//        setTimes(jsonObject.get("times").toString());
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

//    public void setAccountid(String accountid) {
//        this.accountid = accountid == null ? null : accountid.trim();
//    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public void setIat(String iat) {
        this.iat = iat == null ? null : iat.trim();
    }

//    public void setPowerid(String powerid) {
//        this.powerid = powerid == null ? null : powerid.trim();
//    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

//    public void setTimes(String times) {
//        this.times = times == null ? null : times.trim();
//    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber == null ? null : worknumber.trim();
    }

//    public String getAccountid() {
//        return accountid;
//    }

    public String getAccountname() {
        return accountname;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public String getExt() {
        return ext;
    }

    public String getIat() {
        return iat;
    }

//    public String getPowerid() {
//        return powerid;
//    }

    public String getStatus() {
        return status;
    }

//    public String getTimes() {
//        return times;
//    }

    public String getWorknumber() {
        return worknumber;
    }


}
