package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.LocationBean;

public interface LocationSaveListener {

    void onLoadCompleted(LocationBean locationBean);

    void onLoadFailed(String error);
}


