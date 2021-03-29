package com.ctg.servdept.pojo.until;

public class BillEntity {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getXsdno() {
        return xsdno;
    }

    public void setXsdno(String xsdno) {
        this.xsdno = xsdno;
    }

    public double getShoughtpay() {
        return shoughtpay;
    }

    public void setShoughtpay(double shoughtpay) {
        this.shoughtpay = shoughtpay;
    }

    private static final long serialVersionUID = 1L;

    private String market;

    private String xsdno;

    private double shoughtpay;
}
