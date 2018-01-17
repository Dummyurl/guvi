package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.TripBean;
import com.guvi.gt.lataxi.model.TripListBean;

public abstract interface TripListListener {

    void onLoadCompleted(TripListBean tripListBean);

    void onLoadFailed(String error);

}
