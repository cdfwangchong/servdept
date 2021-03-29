package com.ctg.servdept.controller;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import com.ctg.servdept.pojo.dto.LeavedDto;
import com.ctg.servdept.pojo.dto.UserDto;
import com.ctg.servdept.pojo.until.Result;
import com.ctg.servdept.pojo.until.Token;
import com.ctg.servdept.pojo.until.UserEntity;
import com.ctg.servdept.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.ctg.servdept.pojo.until.Constant.*;

@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class LoginController {

    @Autowired
    private LoginService loginserice = null;

    Logger logger = Logger.getLogger(LoginController.class);

    /**
     *登录
     * @param thduser
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result<Map<String, Object>> Login(@RequestBody UserDto thduser) {
        if (thduser == null) {
            logger.error("获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }

        UserDto userdto = new UserDto();
        String userID = thduser.getUserId();
        String station = thduser.getStation();

        String strToEncrypt = thduser.getPassWord();
        //获取加密后的密码
       //String passWord = AES.encrypt(strToEncrypt,key);
        String passWord = DigestUtils.md5DigestAsHex(strToEncrypt.getBytes());

        thduser.setPassWord(passWord);

        userdto.setPassWord(passWord);
        userdto.setUserId(userID);
        userdto.setStation(station);
        Map<String, Object> tokenMap= loginserice.login(userdto);

        return new Result<Map<String, Object>>(sucCode,sucMsg,tokenMap);
    }

    @PostMapping(value = "/qryUser")
    @ResponseBody
    public Result<UserEntity> qryUser(HttpServletRequest request,@RequestBody LeavedDto leavedDto) {
        if (leavedDto == null) {
            logger.error("获取到的对象值为空");
            throw new ServDeptNotFoundException(errCode_5,errMsg_5);
        }

        String token = request.getHeader("Authorization");
        new Token().CheckToken(token);
        UserEntity ue = loginserice.qryUser(leavedDto);

        return new Result<UserEntity>(sucCode,sucMsg,ue);
    }
}
