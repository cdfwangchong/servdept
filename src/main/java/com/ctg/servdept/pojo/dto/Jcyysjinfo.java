package com.ctg.servdept.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JCYYSJINFO
 * @author 
 */
@Data
public class Jcyysjinfo implements Serializable {
    /**
     * 序号
     */
    private BigDecimal yyseq;

    /**
     * 购物卡号
     */
    private String gwkh;

    /**
     * 预约时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yysj;

    /**
     * 取货地点
     */
    private String qhdd;

    /**
     * 原预约时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date preyysj;

    /**
     * 原取货地点
     */
    private String preqhdd;

    /**
     * 修改标志
     */
    private String updflag;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updtime;

    /**
     * 修改次数
     */
    private BigDecimal updcnt;

    /**
     * 发送时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fstime;

    /**
     * 是否取货
     */
    private String isqh;

    /**
     * 是否有效
     */
    private String isyx;

    private static final long serialVersionUID = 1L;
}