package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.FareBean;

public interface TotalFareListener {

    void onLoadCompleted(FareBean fareBean);

    void onLoadFailed(String error);
}
