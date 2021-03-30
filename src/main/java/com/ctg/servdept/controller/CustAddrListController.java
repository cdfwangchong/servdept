package com.ctg.servdept.controller;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.pojo.dto.InsertCustAddrAndListDto;
import com.ctg.servdept.pojo.until.BillEntity;
import com.ctg.servdept.pojo.until.Result;
import com.ctg.servdept.pojo.until.Token;
import com.ctg.servdept.service.CustAddrListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ctg.servdept.pojo.until.Constant.*;

/*
 * project name :返岛提货
 * for:商品寄存接口
 * author：wangc
 * time：2021-3-28
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CustAddrListController {
    @Autowired
    CustAddrListService calService = null;
    Logger logger = Logger.getLogger(CustAddrListController.class);

    @PostMapping("insertdept")
    @ResponseBody
    public Result<String> insertCustAddrList(HttpServletRequest request, @RequestBody InsertCustAddrAndListDto ica){
        if (ica == null) {
            logger.error("返岛取货接口获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";

        for (int i = 0; i < ica.getOrderList().size(); i++) {
            BillEntity pi = ica.getOrderList().get(i);
            logger.info("取到要寄存的提货单："+pi.getXsdno()+"门店："+pi.getMarket());
        }
        boolean bl = calService.insertCustAddrList(ica,worknumber);
        if (bl) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            throw new ServDeptNotFoundException(errCode3,errMsg3);
        }
    }
}
