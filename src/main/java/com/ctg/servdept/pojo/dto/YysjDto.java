package com.ctg.servdept.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class YysjDto {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yysj;

    private String qhdd;

    private String gwkh;

    public String getQhdd() {
        return qhdd;
    }

    public void setQhdd(String qhdd) {
        this.qhdd = qhdd;
    }

    public String getGwkh() {
        return gwkh;
    }

    public void setGwkh(String gwkh) {
        this.gwkh = gwkh;
    }

    public Date getYysj() {
        return yysj;
    }

    public void setYysj(Date yysj) {
        this.yysj = yysj;
    }

}
