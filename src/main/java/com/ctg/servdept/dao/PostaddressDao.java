package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.Jcyysjinfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PostaddressDao {
    Map insert(Map<String, String> param);

    Jcyysjinfo selectByPrimaryKey(String cardId);

    Map updateByPrimaryKey(Map<String, String> param);
}