package com.guvi.gt.lataxi.listeners;


import com.guvi.gt.lataxi.model.AuthBean;
import com.guvi.gt.lataxi.model.UserBean;

public interface EditProfileListener {

    void onLoadCompleted(UserBean userBean);

    void onLoadFailed(String error);

}
