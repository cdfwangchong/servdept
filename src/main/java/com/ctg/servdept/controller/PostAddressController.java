package com.ctg.servdept.controller;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.pojo.dto.Jcyysjinfo;
import com.ctg.servdept.pojo.dto.YysjDto;
import com.ctg.servdept.pojo.until.Login;
import com.ctg.servdept.pojo.until.Result;
import com.ctg.servdept.service.PostAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ctg.servdept.pojo.until.Constant.*;

/*
 * project name :返岛提货
 * for:预约信息管理和新增接口
 * author：wangc
 * time：2021-3-28
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class PostAddressController {
    @Autowired
    PostAddressService paService = null;
    Logger logger = Logger.getLogger(PostAddressController.class);

    /**
     * 预约信息管理接口
     * @param login
     * @return
     */
    @PostMapping("qryyyinfo")
    @ResponseBody
    public Result<Jcyysjinfo> qryPostAddress(@RequestBody Login login) {
        if (login == null) {
            logger.error("预约信息查询接口获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }
        Jcyysjinfo pd = paService.qryPostAddress(login);
        logger.info("取到预约信息管理接口的传入参数"+login.getGwkh());
        return new Result<Jcyysjinfo>(sucCode,sucMsg,pd);
    }

    /**
     * 收货地址新增接口
     * @param paDto
     * @return
     */
    @PostMapping("/insertyyinfo")
    @ResponseBody
    public Result<String> insertPostAddress(@RequestBody YysjDto paDto) {
        if (paDto == null) {
            logger.error("预约信息新增接口获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }
        logger.info("取到新增预约信息接口的传入参数"+paDto.getGwkh()+"详细信息："+paDto.getQhdd()+paDto.getYysj());
        String rs = paService.insertPostAddress(paDto);
        if("1000".equals(rs)) {
            return new Result<String>(sucCode,sucMsg,rs);
        }else {
            throw new ServDeptNotFoundException(errCode,"新增预约信息失败");
        }
    }

    @PostMapping("updateyyinfo")
    @ResponseBody
    public Result<String> updatePostAddress(@RequestBody Jcyysjinfo paDto) {
        if (paDto == null) {
            logger.error("预约信息新增接口获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }
        logger.info("取到更新预约信息接口的传入参数"+paDto.getGwkh()+"详细信息："+paDto.getQhdd()+paDto.getYysj());
        int rs = paService.updatePostAddress(paDto);
        if (rs == 1) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            throw new ServDeptNotFoundException(errCode,"更新预约信息失败");
        }
    }
}
