package com.ctg.servdept.pojo.until;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SellHeadEntity {
    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public Date getSHDPTTIME() {
        return SHDPTTIME;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setSHDPTTIME(Date SHDPTTIME) {
        this.SHDPTTIME = SHDPTTIME;
    }

    public String getSHISTH() {
        return SHISTH;
    }

    public void setSHISTH(String SHISTH) {
        this.SHISTH = SHISTH;
    }

    public String getSHTHFS() {
        return SHTHFS;
    }

    public void setSHTHFS(String SHTHFS) {
        this.SHTHFS = SHTHFS;
    }

    public String getSHSTATUS() {
        return SHSTATUS;
    }

    public void setSHSTATUS(String SHSTATUS) {
        this.SHSTATUS = SHSTATUS;
    }

    public BigDecimal getSHBILLNO() {
        return SHBILLNO;
    }

    public void setSHBILLNO(BigDecimal SHBILLNO) {
        this.SHBILLNO = SHBILLNO;
    }

    public String getSHXSDNO() {
        return SHXSDNO;
    }

    public void setSHXSDNO(String SHXSDNO) {
        this.SHXSDNO = SHXSDNO;
    }

    private String MARKET;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date SHDPTTIME;

    private String SHISTH;

    private String SHTHFS;

    private String SHSTATUS;

    private BigDecimal SHBILLNO;

    private String SHXSDNO;
}
