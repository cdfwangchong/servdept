package com.ctg.servdept.service.Impl;

import com.ctg.servdept.dao.SellDetailDao;
import com.ctg.servdept.pojo.dto.PickBillDto;
import com.ctg.servdept.pojo.dto.PickNumDto;
import com.ctg.servdept.service.QryBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QryBillDetailServiceImpl implements QryBillDetailService {
    @Autowired
    SellDetailDao selldetaildao;

    @Override
    public List<PickBillDto> getselldetail(PickNumDto picknumdto) {
        return selldetaildao.QrySellDetail(picknumdto);
    }
}
