package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.AppStatusBean;
import com.guvi.gt.lataxidriver.model.BasicBean;

/**
 * Created by Jemsheer K D on 14 June, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface AppStatusListener {
    void onLoadCompleted(AppStatusBean appStatusBean);

    void onLoadFailed(String error);
}
