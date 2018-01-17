package com.guvi.gt.lataxidriver.listeners;


public interface PermissionListener {

    void onPermissionCheckCompleted(int requestCode, boolean isPermissionGranted);

}
