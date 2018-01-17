package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.HelpBean;

/**
 * Created by Jemsheer K D on 20 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface HelpListener {
    void onLoadCompleted(HelpBean helpBean);

    void onLoadFailed(String error);
}
