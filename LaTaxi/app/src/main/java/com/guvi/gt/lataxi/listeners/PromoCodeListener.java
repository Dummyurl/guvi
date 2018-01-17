package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.PromoCodeBean;
import com.guvi.gt.lataxi.model.UserBean;

public interface PromoCodeListener {

    void onLoadCompleted(PromoCodeBean promoCodeBean);

    void onLoadFailed(String error);

}
