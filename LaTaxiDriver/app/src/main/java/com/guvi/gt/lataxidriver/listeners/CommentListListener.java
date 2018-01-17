package com.guvi.gt.lataxidriver.listeners;

import com.guvi.gt.lataxidriver.model.CommentListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface CommentListListener {

    void onLoadCompleted(CommentListBean commentListBean);

    void onLoadFailed(String error);
}
