package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.PickBillDto;
import com.ctg.servdept.pojo.dto.PickNumDto;

import java.util.List;

public interface QryBillDetailService {

    List<PickBillDto> getselldetail(PickNumDto picknumdto);
}
