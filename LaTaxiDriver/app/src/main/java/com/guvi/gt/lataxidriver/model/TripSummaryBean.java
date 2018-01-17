package com.guvi.gt.lataxidriver.model;


public class TripSummaryBean extends BasicBean {

    private String totalFare;
    private String baseFare;
    private String laTaxiFee;
    private String toll;
    private String discount;
    private String tax;

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getLaTaxiFee() {
        return laTaxiFee;
    }

    public void setLaTaxiFee(String laTaxiFee) {
        this.laTaxiFee = laTaxiFee;
    }

    public String getToll() {
        return toll;
    }

    public void setToll(String toll) {
        this.toll = toll;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}
