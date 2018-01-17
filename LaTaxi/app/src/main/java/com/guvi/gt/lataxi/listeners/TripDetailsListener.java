package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.TripDetailsBean;

public interface TripDetailsListener {

    void onLoadCompleted(TripDetailsBean tripDetailsBean);

    void onLoadFailed(String error);
}
