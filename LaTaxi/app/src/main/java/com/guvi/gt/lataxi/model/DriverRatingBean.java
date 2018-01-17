package com.guvi.gt.lataxi.model;


import java.util.ArrayList;

public class DriverRatingBean extends BaseBean {

    private String rating;
    private ArrayList badFeedbackList = new ArrayList();
    private ArrayList goodFeedbackList = new ArrayList();
    private String feedback;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList getBadFeedbackList() {
        return badFeedbackList;
    }

    public void setBadFeedbackList(ArrayList badFeedbackList) {
        this.badFeedbackList = badFeedbackList;
    }

    public ArrayList getGoodFeedbackList() {
        return goodFeedbackList;
    }

    public void setGoodFeedbackList(ArrayList goodFeedbackList) {
        this.goodFeedbackList = goodFeedbackList;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
