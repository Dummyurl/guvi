package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.TripFeedbackBean;

public interface TripFeedbackListener {

    void onLoadFailed(String error);

    void onLoadCompleted(TripFeedbackBean tripFeedbackBean);

}
