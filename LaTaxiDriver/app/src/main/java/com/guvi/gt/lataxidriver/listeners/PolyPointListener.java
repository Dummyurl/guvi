package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.PolyPointBean;

/**
 * Created by Jemsheer K D on 09 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface PolyPointListener {
    void onLoadCompleted(PolyPointBean polyPointBean);

    void onLoadFailed(String error);
}
