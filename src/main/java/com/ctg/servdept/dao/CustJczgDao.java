package com.ctg.servdept.dao;

import com.ctg.servdept.pojo.dto.DmzgDto;
import com.ctg.servdept.pojo.until.CustTrip;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustJczgDao {

    Map QryCustJczg(Map param);

    int Update(List<DmzgDto> record);

    Map QryCustTrip(Map param);

//    int UpdateTrip(List<CustTrip> record);

    Map UpdateTrip(Map<String,Object> record);
}
