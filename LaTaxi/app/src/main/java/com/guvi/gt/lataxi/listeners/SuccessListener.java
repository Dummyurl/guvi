package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.SuccessBean;

public interface SuccessListener {

    void onLoadCompleted(SuccessBean successBean);

    void onLoadFailed(String error);
}
