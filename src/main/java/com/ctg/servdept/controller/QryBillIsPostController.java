package com.ctg.servdept.controller;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.pojo.dto.XsdnoDto;
import com.ctg.servdept.pojo.until.*;
import com.ctg.servdept.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ctg.servdept.pojo.until.Constant.*;

/*
 * project name :返岛提货
 * for:未寄存提货单接口和已经寄存的提货单接口
 * author：wangc
 * time：2021-3-28
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class QryBillIsPostController {

    @Autowired
    private QryBillIsPostService qbipService=null;
    Logger logger = Logger.getLogger(QryBillIsPostController.class);

    /**
     * 未邮寄提货单接口
     * @param login
     * @return
     */
    @PostMapping("/qrynotdeptbill")
    @ResponseBody
    public Result<XsdnoDto> qryNotPostBill(HttpServletRequest request, @RequestBody Login login) {
        XsdnoDto xsdnoDto;
        if (login == null){
            logger.error("寄存提货单查询接口传入的参数值为null");
            throw new ServDeptNotFoundException(errCode5,errMsg5);
        }
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";
        xsdnoDto = qbipService.qryNotPostBill(login,worknumber);
        for (int i = 0; i < xsdnoDto.getOrderList().size(); i++) {
            BillEntity be = xsdnoDto.getOrderList().get(i);
            logger.info("取到未寄存提货单接口返回值："+be.getMarket()+"#"+be.getXsdno()+"#"+be.getShoughtpay());
        }
        return new Result<XsdnoDto>(sucCode,sucMsg,xsdnoDto);
    }

    /**
     * 邮寄提货单查询接口
     * @param login
     * @return
     */
    @PostMapping("/qrydepttbill")
    @ResponseBody
    public Result<List<CustDeptlistDetEntity>> qryPostBill(HttpServletRequest request,@RequestBody Login login) {
        List<CustDeptlistDetEntity> beList;

        if (login == null){
            logger.error("邮寄提货单查询接口传入的参数值为null");
            throw new ServDeptNotFoundException(errCode5,errMsg5);
        }
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
        String worknumber = "3859";
        beList = qbipService.qryPostBill(login,worknumber);
        for (int i = 0; i < beList.size(); i++) {
            CustDeptlistDetEntity be;
            be = beList.get(i);
            logger.info("取到邮寄提货单接口返回值："+be.getXsdno()+be.getYyseq()+be.getQhdd()+be.getMarket());
        }
        return new Result<List<CustDeptlistDetEntity>>(sucCode,sucMsg,beList);
    }
}
