package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.RequestBean;

public interface RequestStatusListener {

    void onLoadCompleted(RequestBean requestBean);

    void onLoadFailed(String error);
}
