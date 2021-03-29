package com.ctg.servdept.pojo.until;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CustDeptlistDetEntity {
    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getYysj() {
        return yysj;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    public BigDecimal getYyseq() {
        return yyseq;
    }

    public void setYyseq(BigDecimal yyseq) {
        this.yyseq = yyseq;
    }

    private String seq_no;

    private Date yysj;

    private String qhdd;

    private String xsdno;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private BigDecimal yyseq;

    private String market;
}
