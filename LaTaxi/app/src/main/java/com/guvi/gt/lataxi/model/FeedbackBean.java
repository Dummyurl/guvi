package com.guvi.gt.lataxi.model;


import java.util.ArrayList;

public class FeedbackBean extends BaseBean {

    private String successBean;
    private float rating;
    private ArrayList<String> badFeedBackList = new ArrayList<>();
    private ArrayList<String> goodFeedBackList =new ArrayList<>();
    private String badFeedBackType;
    private String goodFeedBackTYpe;
    private String feedBack;

    public ArrayList<String> getBadFeedBackList() {
        return badFeedBackList;
    }

    public void setBadFeedBackList(ArrayList<String> badFeedBackList) {
        this.badFeedBackList = badFeedBackList;
    }

    public ArrayList<String> getGoodFeedBackList() {
        return goodFeedBackList;
    }

    public void setGoodFeedBackList(ArrayList<String> goodFeedBackList) {
        this.goodFeedBackList = goodFeedBackList;
    }

    public String getSuccessBean() {
        return successBean;
    }

    public void setSuccessBean(String successBean) {
        this.successBean = successBean;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBadFeedBackType() {
        return badFeedBackType;
    }

    public void setBadFeedBackType(String badFeedBackType) {
        this.badFeedBackType = badFeedBackType;
    }

    public String getGoodFeedBackTYpe() {
        return goodFeedBackTYpe;
    }

    public void setGoodFeedBackTYpe(String goodFeedBackTYpe) {
        this.goodFeedBackTYpe = goodFeedBackTYpe;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
