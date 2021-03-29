package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.until.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface LoginDao {
    Map selectByPrimaryKey(Map<String, String> param);

    UserEntity qryUser(String gwkh);
}
