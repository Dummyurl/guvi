package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.FreeRideBean;

public interface FreeRideListener {

    void onLoadCompleted(FreeRideBean freeRideBean);

    void onLoadFailed(String error);

}
