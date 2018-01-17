package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.CarBean;
import com.guvi.gt.lataxi.model.PlaceBean;

public interface PolyLineListener {

    void onLoadFailed(String error);

    void onLoadCompleted(PlaceBean placeBean);
}
