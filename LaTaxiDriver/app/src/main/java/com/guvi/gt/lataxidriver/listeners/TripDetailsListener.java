package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.TripBean;

/**
 * Created by Jemsheer K D on 09 June, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface TripDetailsListener {

    void onLoadCompleted(TripBean tripBean);

    void onLoadFailed(String error);
}
