package com.ctg.servdept.task;

import cn.ctg.exceptionHandle.ExceptionPrintMessage;
import com.alibaba.fastjson.JSONObject;
import com.ctg.servdept.controller.ServDeptExceptionController;
import com.ctg.servdept.dao.CustJczgDao;
import com.ctg.servdept.pojo.dto.CustJczgDto;
import com.ctg.servdept.pojo.dto.CustTripInfo;
import com.ctg.servdept.pojo.dto.DmzgDto;
import com.ctg.servdept.pojo.until.CustTrip;
import com.ctg.servdept.pojo.until.GetCustTripInfo;
import com.ctg.servdept.pojo.until.HttpClientUtil;
import com.ctg.servdept.pojo.until.TableInfo;
import com.ctg.servdept.dao.CustJczgDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustJczgTask {
    @Autowired
    CustJczgDao custJczgdao;

    Logger logger = Logger.getLogger(CustJczgTask.class);

    /**
     * 定时任务-获取顾客寄存资格
     *
     * @return
     */
//    @Scheduled(cron = "* */10 * * * *")
    public void qryJczgTask() {
        List<CustJczgDto> cjlist = null;
        List<DmzgDto> dmzglist = new ArrayList<DmzgDto>();
        try {
            Map param = new HashMap();
            custJczgdao.QryCustJczg(param);
            cjlist = (List<CustJczgDto>) param.get("jcRc");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查询寄存资格失败");
        }
        for (int i = 0; i < cjlist.size(); i++) {
            CustJczgDto cjDto = cjlist.get(i);

            String user_name = cjDto.getGmname();
            String id_type = cjDto.getGmzjlb();
            String card_id = cjDto.getGmzjno();
            //调用海关接口，查询客人是否岛民
            HttpClientUtil htUtil = new HttpClientUtil();
            TableInfo tinfo = new TableInfo();
            tinfo.setBuyerName(user_name);
            tinfo.setIdcardNo(card_id);
            tinfo.setIdcardType(id_type);

            JSONObject jsonObject = htUtil.doPost(tinfo);
            String status = jsonObject.getString("status");
            String data = jsonObject.getString("data");
            String msg = jsonObject.getString("msg");
            logger.info("获取海关岛民身份接口返回值" + status + data + msg + card_id);
            logger.info("获取岛内居民信息返回标志：" + "用户名：" + user_name + "身份证：" + card_id);
            DmzgDto dmzgdto = new DmzgDto();
            if ("error".equals(status)) {
                dmzgdto.setIsdm("E");//error
                dmzgdto.setGmname(user_name);
                dmzgdto.setGmzjlb(id_type);
                dmzgdto.setGmzjno(card_id);
                logger.info("海关验证岛民身份失败");
                //throw new CustDeptNotFoundException(errCode, "海关验证身份接口返回失败");
            } else {
                if ("true".equals(data)) {
                    dmzgdto.setIsdm("Y");
                    dmzgdto.setGmname(user_name);
                    dmzgdto.setGmzjlb(id_type);
                    dmzgdto.setGmzjno(card_id);
                    logger.info(user_name + card_id + "是岛内居民，可以申请办理寄存业务");
                    //return new Result<String>(sucCode, sucMsg, "true");
                } else {
                    dmzgdto.setIsdm("N");
                    dmzgdto.setGmname(user_name);
                    dmzgdto.setGmzjlb(id_type);
                    dmzgdto.setGmzjno(card_id);
                    logger.info(user_name + card_id + "非岛内居民，不能申请办理寄存业务");
                    //return new Result<String>(sucCode, sucMsg, "false");
                }
            }
            if (!dmzgdto.getIsdm().isEmpty()) {
                dmzglist.add(dmzgdto);
            }
        }
        //将海关返回值，更新表
        try {
            if (dmzglist.size() > 0) {
                for (int i = 0; i < dmzglist.size(); i++) {
                    DmzgDto dd = dmzglist.get(i);
                    logger.info("取到要更新岛民标志的顾客：" + dd.getGmname() + "证件号：" + dd.getGmzjno()
                            + "证件类型：" + dd.getGmzjlb() + "是否岛民：" + dd.getIsdm());
                }
                custJczgdao.Update(dmzglist);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("是否岛民标志更新异常");
        }
    }

    /**
     * 查询旅客的成行记录
     */
    @Scheduled(cron = "* */10 * * * *")
//    @Scheduled(cron = "* * 0-4 * * ?")//每天0-4点每小时执行一次
    public void QryCustTrip() {
        List<CustTripInfo> cjlist = null;
        List<CustTrip> ctlist = new ArrayList<CustTrip>();
        try {
            Map param = new HashMap();
            custJczgdao.QryCustTrip(param);
            cjlist = (List<CustTripInfo>) param.get("tripRc");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查询旅客成行记录失败");
        }

        for (int i = 0; i < cjlist.size(); i++) {
            CustTripInfo ctDto = cjlist.get(i);
            String saleDate = ctDto.getSaleDate();
            String fltDate = ctDto.getFltDate();
            String fltNumber = ctDto.getFltNumber();
            String pasName = ctDto.getPasName();
            String pasNipp = ctDto.getPasNipp();

            //调用中航信接口，查询客人成行记录
            GetCustTripInfo gctInfo = new GetCustTripInfo();

            JSONObject jsonObject = gctInfo.doPost(ctDto);
            String resultCode = jsonObject.getString("resultCode");
            String msg = jsonObject.getString("msg");
            String flag = null;
            String flDate;
            CustTrip ct = new CustTrip();
            if (msg.equals("failed") && resultCode.equals("200")) {
                //解析date下的属性
                String data = jsonObject.getString("data");
                ct.setCxFlag("E");
                logger.info("获取中航信接口返回值" + resultCode + data + msg + pasNipp);
            }
            if (msg.equals("success") && resultCode.equals("200")) {
                //解析date下的属性
                JSONObject data = jsonObject.getJSONObject("data");
                flag = data.getString("flag");
                flDate = data.getString("fltDate");
                logger.info("获取中航信接口返回值" + resultCode + flag + msg + pasNipp);
            }
            logger.info("获取中航信信息返回标志：" + "用户名：" + pasName + "身份证：" + pasNipp);

            if ("N".equals(flag) || "N1".equals(flag)) {
                ct.setCxFlag("N");//error
                ct.setPasNipp(pasNipp);
//                System.out.println(pasNipp);
                ct.setFltNumber(fltNumber);
                ct.setFltDate(fltDate);
                ct.setPasName(pasName);
                ct.setSaleDate(saleDate);
                logger.info("中航信返回信息为：失败");
            } else if ("Y1".equals(flag) || "Y2".equals(flag) || "Y3".equals(flag)) {
                ct.setCxFlag("Y");//error
                ct.setPasNipp(pasNipp);
                ct.setFltNumber(fltNumber);
                ct.setFltDate(fltDate);
                ct.setPasName(pasName);
                ct.setSaleDate(saleDate);
                logger.info(pasName + pasNipp + "有离岛成行记录，可以申请办理寄存业务");
            }else {
                ct.setCxFlag("E");//error
                ct.setPasNipp(pasNipp);
                ct.setFltNumber(fltNumber);
                ct.setFltDate(fltDate);
                ct.setPasName(pasName);
                ct.setSaleDate(saleDate);
                logger.info(pasName + pasNipp + "调用中航信接口失败");
            }
            ctlist.add(ct);
        }
        //将海关返回值，更新表
        try {
            if (ctlist.size() > 0) {
                for (int i = 0; i <ctlist.size() ; i++) {
                    CustTrip ct = ctlist.get(i);
                    logger.info("取到要更新成行记录的顾客："+ct.getPasName()+"证件号："+ct.getPasNipp()
                           +"是否离岛："+ct.getCxFlag());
                }
                custJczgdao.UpdateTrip(ctlist);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("是否成行标志更新异常");
        }
    }
}