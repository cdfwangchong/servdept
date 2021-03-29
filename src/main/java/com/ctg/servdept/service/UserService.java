package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.Postvercode;

import java.util.Map;

public interface UserService {
    Map<String, String> getCoupon(Map<String, String> param);

    Map<String, String> login(Map<String, String> param);

    Map<String, String> getVercode(Map<String, String> param);

    Map<String, String> weChat(Map<String, String> param);

    int insert(Postvercode pv);

    Postvercode selectByPrimaryKey(Map<String, String> param);

}
