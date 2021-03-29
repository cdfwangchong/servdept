package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.LeavedDto;
import com.ctg.servdept.pojo.dto.UserDto;
import com.ctg.servdept.pojo.until.UserEntity;

import java.util.Map;

public interface LoginService {
    Map<String, Object> login(UserDto userDto);

    UserEntity qryUser(LeavedDto leavedDto);
}
