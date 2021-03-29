package com.ctg.servdept.service;

import com.ctg.servdept.pojo.dto.Jcyysjinfo;
import com.ctg.servdept.pojo.dto.YysjDto;
import com.ctg.servdept.pojo.until.Login;

import java.util.Map;

public interface PostAddressService {
    Jcyysjinfo qryPostAddress(Login login);

    Map insertPostAddress(YysjDto ipaDto);

    int updatePostAddress(Jcyysjinfo ipaDto);
}
