package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.InsertCustAddrAndListDto;

public interface CustAddrListService {
    boolean insertCustAddrList(InsertCustAddrAndListDto ica,String worknumber);
}
