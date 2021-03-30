package com.ctg.servdept.service.Impl;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import cn.ctg.exceptionHandle.ExceptionPrintMessage;
import com.ctg.servdept.dao.QryBillIsPostDao;
import com.ctg.servdept.pojo.dto.XsdnoDto;
import com.ctg.servdept.pojo.until.BillEntity;
import com.ctg.servdept.pojo.until.CustDeptlistDetEntity;
import com.ctg.servdept.pojo.until.Login;
import com.ctg.servdept.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ctg.servdept.pojo.until.Constant.*;

@Service
public class QryBillIsPostServiceImpl implements QryBillIsPostService {
    @Autowired
    private QryBillIsPostDao qbipDao=null;
    Logger logger = Logger.getLogger(QryBillIsPostServiceImpl.class);

    /**
     * 未寄存提货单接口
     * @param login
     * @return
     */
    @Override
    public XsdnoDto qryNotPostBill(Login login,String worknumber) {
        List<BillEntity> beList;
        String ret_card;
        String ret_name;
        Map param = new HashMap<String,String>();
        XsdnoDto xsdnoDto = new XsdnoDto();
        try {
            param.put("gwkh",login.getGwkh());
            param.put("operator",worknumber);
            qbipDao.qryNotPostBill(param);
            //取出结果集
            beList = (List<BillEntity>) param.get("wyjRc");
            ret_name = (String)param.get("ret_name");
            ret_card = (String)param.get("ret_card");

            xsdnoDto.setOrderList(beList);
            xsdnoDto.setRet_card(ret_card);
            xsdnoDto.setRet_name(ret_name);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查找未邮寄的提货单存储过程返回值异常");
            throw new ServDeptNotFoundException(errCode,errMsg);
        }
        //取出ret_flag
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (!"1".equals(ret_flag)) {
            logger.error(ret_msg);
            throw new ServDeptNotFoundException(errCode6,ret_msg);
        }
        return xsdnoDto;
    }

    /**
     * 寄存提货单查询接口
     * @param login
     * @return
     */
    @Override
    public List<CustDeptlistDetEntity> qryPostBill(Login login,String worknumber) {
        Map param = new HashMap<String,String>();
        param.put("gwkh",login.getGwkh());
        param.put("operator",worknumber);
        List<CustDeptlistDetEntity> beyList;
        try {
            qbipDao.qryPostBill(param);
            //取出结果集
            beyList = (List<CustDeptlistDetEntity>) param.get("yjRc");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查找已邮寄的提货单存储过程返回值异常");
            throw new ServDeptNotFoundException(errCode,errMsg);
        }
        //取出ret_flag
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (!"1".equals(ret_flag)) {
            logger.error(ret_msg);
            throw new ServDeptNotFoundException(errCode6,ret_msg);
        }
        return beyList;
    }
}
