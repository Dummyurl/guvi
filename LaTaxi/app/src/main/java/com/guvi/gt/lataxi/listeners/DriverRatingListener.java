package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.DriverRatingBean;

public interface DriverRatingListener {

    void onLoadCompleted(DriverRatingBean driverRatingBean);

    void onLoadFailed(String error);
}


