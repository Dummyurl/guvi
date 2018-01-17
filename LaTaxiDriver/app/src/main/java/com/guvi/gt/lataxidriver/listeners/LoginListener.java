package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.AuthBean;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface LoginListener {

    void onLoadCompleted(AuthBean authBean);

    void onLoadFailed(String error);

}
