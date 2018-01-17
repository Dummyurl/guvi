package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.DriverBean;

public interface DriverDetailsListener {

    void onLoadCompleted(DriverBean driverBean);

    void onLoadFailed(String error);
}
