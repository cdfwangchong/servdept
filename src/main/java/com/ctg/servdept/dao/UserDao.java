package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.GwkMainDto;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
*@author wangchong 
*2018
*/
@Repository
public interface UserDao {

    GwkMainDto selectByPrimaryKey(Map<String, String> param);
}
