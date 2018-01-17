package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.AccessibilityBean;

public interface AccessibilityListener {

    void onLoadCompleted(AccessibilityBean accessibilityBean);

    void onLoadFailed(String error);
}
