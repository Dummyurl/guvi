package com.guvi.gt.lataxidriver.listeners;


import com.guvi.gt.lataxidriver.model.AuthBean;
import com.guvi.gt.lataxidriver.model.TripSummaryBean;

public interface TripSummaryListener {

    void onLoadCompleted(TripSummaryBean tripSummaryBean);

    void onLoadFailed(String error);
}
