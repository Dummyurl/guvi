package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.CarBean;
import com.guvi.gt.lataxi.model.DriverBean;

public interface AppStatusListener {

    void onLoadFailed(String error);

    void onLoadCompleted(DriverBean driverBean);

}
