package com.ctg.servdept.service.Impl;

import com.ctg.servdept.dao.UserDao;
import com.ctg.servdept.pojo.dto.Postvercode;
import com.ctg.servdept.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
*@author wangchong
*2021
*/
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;

	public Map<String, String> getCoupon(Map<String, String> param) {
		
		return userdao.registerUser(param);
	}

	public Map<String, String> login(Map<String, String> param) {
		
		return userdao.login(param);
	}

	public Map<String, String> getVercode(Map<String, String> param) {
		
		return userdao.getVercode(param);
	}

	public Map<String, String> weChat(Map<String, String> param) {
		
		return userdao.weChat(param);
	}

    public int insert(Postvercode pv) {

        return userdao.insert(pv);
    }

    public Postvercode selectByPrimaryKey(Map<String, String> param) {

        return userdao.selectByPrimaryKey(param);
    }

}
