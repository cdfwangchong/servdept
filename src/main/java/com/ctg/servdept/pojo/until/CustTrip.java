package com.ctg.servdept.pojo.until;

public class CustTrip {
    public String getFltNumber() {
        return fltNumber;
    }

    public void setFltNumber(String fltNumber) {
        this.fltNumber = fltNumber;
    }

    public String getFltDate() {
        return fltDate;
    }

    public void setFltDate(String fltDate) {
        this.fltDate = fltDate;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getPasName() {
        return pasName;
    }

    public void setPasName(String pasName) {
        this.pasName = pasName;
    }

    public String getPasNipp() {
        return pasNipp;
    }

    public void setPasNipp(String pasNipp) {
        this.pasNipp = pasNipp;
    }

    public String getCxFlag() {
        return cxFlag;
    }

    public void setCxFlag(String cxFlag) {
        this.cxFlag = cxFlag;
    }

    private String fltNumber;

    private String fltDate;

    private String saleDate;

    private String pasName;

    private String pasNipp;
    //成行标志
    private String cxFlag;
}
