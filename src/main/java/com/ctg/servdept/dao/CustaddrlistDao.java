package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.Custdeptlist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustaddrlistDao {

    int insert(List<Custdeptlist> record);

}