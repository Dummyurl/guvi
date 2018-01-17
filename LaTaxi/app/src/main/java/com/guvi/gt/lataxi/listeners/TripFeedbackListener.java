package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.CarBean;
import com.guvi.gt.lataxi.model.TripFeedbackBean;

/**
 * Created by SIB-QC4 on 4/12/2017.
 */

public interface TripFeedbackListener {

    void onLoadFailed(String error);

    void onLoadCompleted(TripFeedbackBean tripFeedbackBean);
}
