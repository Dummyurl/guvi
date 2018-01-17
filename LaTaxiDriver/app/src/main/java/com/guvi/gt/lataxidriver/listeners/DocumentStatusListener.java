package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.DocumentStatusBean;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface DocumentStatusListener {

    void onLoadCompleted(DocumentStatusBean documentStatusBean);

    void onLoadFailed(String error);
}
