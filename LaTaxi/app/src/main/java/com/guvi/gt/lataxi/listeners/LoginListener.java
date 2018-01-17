package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.AuthBean;

public interface LoginListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
