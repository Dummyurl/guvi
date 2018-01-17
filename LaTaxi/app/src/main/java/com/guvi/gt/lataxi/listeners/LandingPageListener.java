package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.CarBean;
import com.guvi.gt.lataxi.model.LandingPageBean;

public interface LandingPageListener {

    void onLoadFailed(String error);

    void onLoadCompleted(LandingPageBean landingPageBean);

}
