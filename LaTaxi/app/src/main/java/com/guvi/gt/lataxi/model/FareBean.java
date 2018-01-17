package com.guvi.gt.lataxi.model;


public class FareBean extends BaseBean {

    private String totalFare;
    private String estimatedFare;

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getEstimatedFare() {
        return estimatedFare;
    }

    public void setEstimatedFare(String estimatedFare) {
        this.estimatedFare = estimatedFare;
    }
}
