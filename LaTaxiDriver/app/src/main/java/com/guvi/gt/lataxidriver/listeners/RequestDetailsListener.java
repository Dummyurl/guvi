package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.RequestDetailsBean;

/**
 * Created by Jemsheer K D on 08 June, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface RequestDetailsListener {

    void onLoadCompleted(RequestDetailsBean requestDetailsBean);

    void onLoadFailed(String error);
}
