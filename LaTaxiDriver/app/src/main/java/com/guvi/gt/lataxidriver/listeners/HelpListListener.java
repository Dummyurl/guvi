package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.HelpListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface HelpListListener {
    void onLoadCompleted(HelpListBean helpListBean);

    void onLoadFailed(String error);
}
