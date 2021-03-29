package com.ctg.servdept.service.Impl;

import cn.ctg.exceptionHandle.ExceptionPrintMessage;
import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.dao.LoginDao;
import com.ctg.servdept.pojo.dto.LeavedDto;
import com.ctg.servdept.pojo.dto.UserDto;
import com.ctg.servdept.pojo.until.Jwt;
import com.ctg.servdept.pojo.until.UserEntity;
import com.ctg.servdept.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ctg.servdept.pojo.until.Constant.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao logindao = null;

    Logger logger = Logger.getLogger(LoginServiceImpl.class);

    /**
     * @param userDto
     * @return
     */
    @Override
    public Map<String, Object> login(UserDto userDto) {
        Map<String, Object> payload = new HashMap<String, Object>();
        try {
            logger.info("取到登录信息"+userDto.getUserId()+"@"+userDto.getPassWord());

            Map param = new HashMap<String, Object>();
            param.put("i_userId",userDto.getUserId());
            param.put("i_passWord",userDto.getPassWord());
            param.put("i_deptId",userDto.getStation());

            logindao.selectByPrimaryKey(param);

            if (param.get("user_code") == null||"".equals(param.get("user_code"))) {
                logger.error("员工ID在表中不存在");
                throw new ServDeptNotFoundException(errCode_2,errMsg_2);
            }else {
                String worknumber = (String) param.get("user_code");//员工工号
                String departmentid = (String) param.get("dept_id");// 部门id
                String status = (String) param.get("o_status");//状态
                String accountname = (String) param.get("user_name");// 用户名称

                Date date = new Date();
                payload.put("accountname",accountname);// 用户名称
                payload.put("departmentid",departmentid);// 部门id
                payload.put("status",status);//状态
                payload.put("worknumber",worknumber);//员工工号
                payload.put("iat", date.getTime());// 生成时间
                payload.put("ext", date.getTime() + 10000 * 1000 * 60 * 60 * 24);// 过期时间1 小时 单位是毫秒

                //得到token
                String token = new Jwt().createToken(payload);
                payload.put("token",token);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("员工号不存在或者密码错误");
            throw new ServDeptNotFoundException(errCode_2,errMsg_2);
        }
        return payload;
    }

    @Override
    public UserEntity qryUser(LeavedDto leavedDto) {
        logger.info("取到顾客购物卡号"+leavedDto.getGwkh());
        UserEntity ue;
        try {
            ue = logindao.qryUser(leavedDto.getGwkh());
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("购物卡"+leavedDto.getGwkh()+"在用户表中不存在");
            throw new ServDeptNotFoundException(errCode_16,errMsg_16);
        }
        if (ue == null) {
            throw new ServDeptNotFoundException(errCode_16,errMsg_16);
        }else {
            return ue;
        }
    }
}
