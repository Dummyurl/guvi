package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.ProfileBean;


public interface ProfileListener {

    void onLoadCompleted(ProfileBean profileBean);

    void onLoadFailed(String error);
}
