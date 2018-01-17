package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.RatingDetailsBean;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface RatingDetailsListener {
    void onLoadCompleted(RatingDetailsBean ratingDetailsBean);

    void onLoadFailed(String error);

}
