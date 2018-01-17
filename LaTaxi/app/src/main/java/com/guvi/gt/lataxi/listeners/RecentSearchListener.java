package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.RecentSearchBean;

public interface RecentSearchListener {

    void onLoadCompleted(RecentSearchBean recentSearchBean);

    void onLoadFailed(String webErrorMsg);
}
