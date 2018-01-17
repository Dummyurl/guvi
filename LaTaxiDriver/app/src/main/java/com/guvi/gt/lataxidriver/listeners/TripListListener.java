package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.TripListBean;

/**
 * Created by Jemsheer K D on 08 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface TripListListener {

    void onLoadCompleted(TripListBean tripListBean);

    void onLoadFailed(String error);

}
