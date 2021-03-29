package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.Userlist;
import org.springframework.stereotype.Repository;

@Repository
public interface UserlistDao {

    Userlist selectByPrimaryKey(String openId);

}