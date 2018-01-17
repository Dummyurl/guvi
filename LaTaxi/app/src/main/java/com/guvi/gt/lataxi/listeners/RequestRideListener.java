package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.FreeRideBean;
import com.guvi.gt.lataxi.model.RequestBean;

public interface RequestRideListener {

    void onLoadCompleted(RequestBean requestBean);

    void onLoadFailed(String error);
}
