package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.OTPBean;
import com.guvi.gt.lataxi.model.PromoCodeBean;



public interface OTPSubmitListener {

    void onLoadCompleted(OTPBean otpBean);

    void onLoadFailed(String error);

}
