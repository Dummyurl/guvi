package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.AuthBean;
import com.guvi.gt.lataxi.model.BasicBean;

public interface OTPResendCodeListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);
}
