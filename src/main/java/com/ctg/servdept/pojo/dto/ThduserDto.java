package com.ctg.servdept.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * THDUSER
 * @author 
 */
@Data
public class ThduserDto implements Serializable {
    /**
     * 工号
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 提货点
     */
    private String deptId;

    /**
     * 密码
     */
    private String userCode;

    /**
     * 账号状态
     */
    private String status;

    private static final long serialVersionUID = 1L;
}