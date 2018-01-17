package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.SearchResultsBean;

public interface SearchResultsListener {

    void onLoadCompleted(SearchResultsBean searchResultsBean);

    void onLoadFailed(String webErrorMsg);
}
