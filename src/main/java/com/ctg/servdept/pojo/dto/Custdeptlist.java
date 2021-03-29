package com.ctg.servdept.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CUSTDEPTLIST
 * @author 
 */
@Data
public class Custdeptlist implements Serializable {
    /**
     * 序号
     */
    private BigDecimal seqid;

    /**
     * 购物卡号
     */
    private String gwkh;

    /**
     * 手机号码
     */
    private String telNum;

    /**
     * 门店
     */
    private String market;

    /**
     * 预约时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date yysj;

    /**
     * 取货提点
     */
    private String qhdd;

    /**
     * 申请提货单号,用| 分隔
     */
    private String xsdno;

    /**
     * 发送时间
     */
    private Date fsdate;

    /**
     * 包裹个数
     */
    private int pkgcnt;

    /**
     * 处理标志 Y申请寄存或取消申请寄存
     */
    private String flag;

    /**
     * 错误代码
     */
    private BigDecimal errno;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 取消申请的序号
     */
    private BigDecimal seqidC;

    /**
     * 预约序号
     */
    private BigDecimal yyseq;

    /**
     * 类型
     */
    private String type;

    /**
     * 操作员
     */
    private String operator;

    private static final long serialVersionUID = 1L;
}