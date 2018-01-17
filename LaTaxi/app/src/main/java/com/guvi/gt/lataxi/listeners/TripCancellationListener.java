package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.RequestBean;
import com.guvi.gt.lataxi.model.TripCancellationBean;

public interface TripCancellationListener {

    void onLoadCompleted(TripCancellationBean tripCancellationBean);

    void onLoadFailed(String error);
}
