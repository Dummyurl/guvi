package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.WeeklyEarningsBean;

/**
 * Created by Jemsheer K D on 16 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface WeeklyEarningsListener {

    void onLoadCompleted(WeeklyEarningsBean weeklyEarningsBean);

    void onLoadFailed(String error);
}
