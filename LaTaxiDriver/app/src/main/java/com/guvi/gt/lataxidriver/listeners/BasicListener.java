package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.BasicBean;

public interface BasicListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);
}
