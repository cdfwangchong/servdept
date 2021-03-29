package com.ctg.servdept.pojo.until;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class JcXsdbillEntity {
    public String getXsdno() {
        return xsdno;
    }

    public void setXsdno(String xsdno) {
        this.xsdno = xsdno;
    }

    public Date getYysj() {
        return yysj;
    }

    public void setYysj(Date yysj) {
        this.yysj = yysj;
    }

    public String getQhdd() {
        return qhdd;
    }

    public void setQhdd(String qhdd) {
        this.qhdd = qhdd;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getIsth() {
        return isth;
    }

    public void setIsth(String isth) {
        this.isth = isth;
    }

    public String getZxdh() {
        return zxdh;
    }

    public void setZxdh(String zxdh) {
        this.zxdh = zxdh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String xsdno;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yysj;

    private String qhdd;

    private String market;

    private String isth;

    private String zxdh="";

    private String status="";
}
