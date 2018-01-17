package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.LandingPageBean;
import com.guvi.gt.lataxi.model.TripListBean;

public interface LandingPageDetailsListener {

    void onLoadCompleted(LandingPageBean landingPageListBean);

    void onLoadFailed(String error);
}
