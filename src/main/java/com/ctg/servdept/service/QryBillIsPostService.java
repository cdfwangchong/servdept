package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.XsdnoDto;
import com.ctg.servdept.pojo.until.CustDeptlistDetEntity;
import com.ctg.servdept.pojo.until.Login;

import java.util.List;


public interface QryBillIsPostService {

    XsdnoDto qryNotPostBill(Login login);

    List<CustDeptlistDetEntity> qryPostBill(Login login);

}
