package com.ctg.servdept.service.Impl;

import cn.ctg.exceptionHandle.ServDeptNotFoundException;
import cn.ctg.exceptionHandle.ExceptionPrintMessage;
import com.ctg.servdept.dao.PostaddressDao;
import com.ctg.servdept.dao.UserDao;
import com.ctg.servdept.pojo.dto.GwkMainDto;
import com.ctg.servdept.pojo.dto.Jcyysjinfo;
import com.ctg.servdept.pojo.dto.YysjDto;
import com.ctg.servdept.pojo.until.Login;
import com.ctg.servdept.service.PostAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ctg.servdept.pojo.until.Constant.*;

@Service
public class PostAddressServiceImpl implements PostAddressService {

    @Autowired
    PostaddressDao paDao;

    @Autowired
    UserDao userDao;

    Logger logger = Logger.getLogger(PostAddressServiceImpl.class);

    /**
     * 查询邮寄地址
     * @param login
     * @return
     */
    @Override
    public Jcyysjinfo qryPostAddress(Login login) {
        //查出顾客的购物卡号
        GwkMainDto ul = null;
        Map param = new  HashMap();
        String gwkh = null;
        try {
            gwkh = login.getGwkh();
            param.put("gwkh",gwkh);
            ul = userDao.selectByPrimaryKey(param);
        } catch (Exception e) {
            logger.error(gwkh+"不存在");
        }
        String cardId = ul.getGwkh();//客人的购物卡号
        logger.info("获取顾客地址前查出客人购物卡号"+cardId);
        Jcyysjinfo paDto;
        try {
            paDto = paDao.selectByPrimaryKey(cardId);
            if (paDto != null) {
                logger.info("取到顾客"+ul.getUserName()+"的预约信息"+paDto.getQhdd()+
                        paDto.getYysj()+paDto.getYyseq());
            }else {
                logger.error("获取到的对象值为空");
                throw new ServDeptNotFoundException(errCode19,errMsg19);
            }
        } catch (Exception e) {
            logger.error(cardId+"无预约信息");
            return new Jcyysjinfo();
        }
        return paDto;
    }

    /**
     * 新增邮寄地址
     * @param ipdDto
     * @return
     */
    @Override
    public String insertPostAddress(YysjDto ipdDto) {
        //查出顾客的购物卡号
        GwkMainDto ul = null;
        Map userparam = new  HashMap();
        String gwkh=null;
        try {
            gwkh = ipdDto.getGwkh();
            userparam.put("gwkh",gwkh);
            ul = userDao.selectByPrimaryKey(userparam);
        } catch (Exception e) {
            logger.error(gwkh+"不存在");
        }
        ipdDto.setGwkh(gwkh);
        logger.info("新增预约信息前查出客人购物卡号"+gwkh);
        Map param = new HashMap<String,String>();
        Map retparam = new HashMap<String,String>();
        String ret_flag;
        try {
            param.put("gwkh",ipdDto.getGwkh());
            param.put("yysj",ipdDto.getYysj());
            param.put("qhdd",ipdDto.getQhdd());
            paDao.insert(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("预约信息写入异常");
            throw new ServDeptNotFoundException(errCode,errMsg);
        }
        ret_flag = (String) param.get("ret_flag");
        if (ret_flag.equals("1000")) {
            logger.info("顾客"+ul.getUserName()+"预约信息新增成功");
        }
        if (ret_flag.equals("1002")) {
            logger.info("顾客"+ul.getUserName()+"有未取货的寄存商品，不可以重新预约");
            throw new ServDeptNotFoundException(errCode,"顾客"+ul.getUserName()+"有未取货的寄存商品，不可以重新预约");
        }
        if (ret_flag.equals("1003")) {
            logger.info("顾客"+ul.getUserName()+"新增预约信息异常");
            throw new ServDeptNotFoundException(errCode,"顾客"+ul.getUserName()+"新增预约信息异常");
        }
        if (ret_flag.equals("1004")) {
            logger.info("预约第二天寄存取货，要在16:00前");
            throw new ServDeptNotFoundException(errCode,"预约第二天寄存取货，要在16:00前");
        }
        if (ret_flag.equals("1005")) {
            logger.info("没有成行记录，不可以登记预约信息");
            throw new ServDeptNotFoundException(errCode,"没有成行记录，不可以登记预约信息");
        }
        return ret_flag;
    }

    @Override
    public int updatePostAddress(Jcyysjinfo ipaDto) {
        //查出顾客的购物卡号
        GwkMainDto ul = null;
        Map userparam = new  HashMap();
        String gwkh=null;
        try {
            gwkh = ipaDto.getGwkh();
            userparam.put("gwkh",gwkh);
            ul = userDao.selectByPrimaryKey(userparam);
        } catch (Exception e) {
            logger.error(gwkh+"不存在");
        }
        ipaDto.setGwkh(gwkh);
        logger.info("更新预约信息前查出客人购物卡号"+gwkh);
        int result=0;
        Map param = new HashMap<String,String>();
        try {
            param.put("yyseq",ipaDto.getYyseq());
            param.put("yysj",ipaDto.getYysj());
            param.put("qhdd",ipaDto.getQhdd());
            param.put("preyysj",ipaDto.getPreyysj());
            param.put("preqhdd",ipaDto.getPreqhdd());

            paDao.updateByPrimaryKey(param);
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("预约信息管理表更新异常");
            throw new ServDeptNotFoundException(errCode,errMsg);
        }
            String ret_flag = (String) param.get("ret_flag");
            if (ret_flag.equals("1000")) {
                logger.info("顾客"+ul.getUserName()+"预约信息新增成功");
                result = 1;
            }
            if (ret_flag.equals("1001")) {
                logger.info("顾客"+ul.getUserName()+"预约信息已经被修改过，不可以多次更新");
                throw new ServDeptNotFoundException(errCode,"预约信息已经被修改过，不可以多次更新");
            }
            if (ret_flag.equals("1002")) {
                logger.info("顾客"+ul.getUserName()+"预约信息不存在");
                throw new ServDeptNotFoundException(errCode,"顾客"+ul.getUserName()+"预约信息不存在");
            }
            if (ret_flag.equals("1003")) {
                logger.info("顾客"+ul.getUserName()+"更新预约信息写入表异常");
                throw new ServDeptNotFoundException(errCode,"顾客"+ul.getUserName()+"更新预约信息写入表异常");
            }
            if (ret_flag.equals("1004")) {
                logger.info("取货时间小于48内的预约申请，要在16:00前提交");
                throw new ServDeptNotFoundException(errCode,"取货时间小于48内的预约申请，要在16:00前提交");
            }
        if (ret_flag.equals("1005")) {
            logger.info("取货时间小于48内的预约申请，要在16:00前提交");
            throw new ServDeptNotFoundException(errCode,"修改取货时间，须提前48内预约申请");
        }
        return result;
    }
}
