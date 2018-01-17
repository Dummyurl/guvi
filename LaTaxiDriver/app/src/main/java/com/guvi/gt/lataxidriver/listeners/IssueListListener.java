package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.IssueListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface IssueListListener {

    void onLoadCompleted(IssueListBean issueListBean);

    void onLoadFailed(String error);

}
