package com.ctg.servdept.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HoldByDateDto {
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Date getYysj() {
        return yysj;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setYysj(Date yysj) {
        this.yysj = yysj;
    }

    public String getQhdd() {
        return qhdd;
    }

    public void setQhdd(String qhdd) {
        this.qhdd = qhdd;
    }

    public String getXsdno() {
        return xsdno;
    }

    public void setXsdno(String xsdno) {
        this.xsdno = xsdno;
    }

    public String getTmpCode() {
        return tmpCode;
    }

    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode;
    }

    public String getZctype() {
        return zctype;
    }

    public void setZctype(String zctype) {
        this.zctype = zctype;
    }

    /**
     * 取货地点
     */
    private String qhdd;

    /**
     * 门店
     */
    private String market;

    /**
     * 离岛日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date yysj;

    /**
     * 取货单号
     */
    private String xsdno;

    /**
     * 装箱单号
     */
    private String tmpCode;

    /**
     * 暂存类型
     */
    private String zctype;

}
