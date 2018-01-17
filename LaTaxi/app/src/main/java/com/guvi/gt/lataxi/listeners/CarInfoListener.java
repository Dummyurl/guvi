package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.CarBean;
import com.guvi.gt.lataxi.model.UserBean;

public interface CarInfoListener {

    void onLoadFailed(String error);

    void onLoadCompleted(CarBean carBean);

}
