package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.PolyPointsBean;

public interface PolyPointsListener {

    void onLoadFailed(String error);

    void onLoadCompleted(PolyPointsBean polyPointsBean);
}
