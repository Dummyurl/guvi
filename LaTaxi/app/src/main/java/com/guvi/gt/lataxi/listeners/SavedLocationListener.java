package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.AuthBean;
import com.guvi.gt.lataxi.model.LocationBean;

public interface SavedLocationListener {

    void onLoadCompleted(LocationBean locationBean);

    void onLoadFailed(String error);

}
