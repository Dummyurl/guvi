package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.AuthBean;

public interface PhoneRegistrationListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);
}
