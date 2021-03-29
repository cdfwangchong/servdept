package com.ctg.servdept.pojo.dto;

import com.ctg.servdept.pojo.until.BillEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InsertCustAddrAndListDto {
    public String getGwkh() {
        return gwkh;
    }

    public void setGwkh(String gwkh) {
        this.gwkh = gwkh;
    }

    public List<BillEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BillEntity> orderList) {
        this.orderList = orderList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(BigDecimal seq_no) {
        this.seq_no = seq_no;
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

    public BigDecimal getYyseq() {
        return yyseq;
    }

    public void setYyseq(BigDecimal yyseq) {
        this.yyseq = yyseq;
    }

    private String gwkh;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yysj;

    private String qhdd;

    private BigDecimal yyseq;

    private String type;

    private BigDecimal seq_no;

    List<BillEntity> orderList;

}
