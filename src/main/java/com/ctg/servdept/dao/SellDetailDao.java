package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.PickBillDto;
import com.ctg.servdept.pojo.dto.PickNumDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellDetailDao {
    List<PickBillDto> QrySellDetail(PickNumDto picknumdto);
}
