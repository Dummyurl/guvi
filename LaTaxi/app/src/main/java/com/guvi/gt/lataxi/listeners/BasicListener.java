package com.guvi.gt.lataxi.listeners;

import com.guvi.gt.lataxi.model.BaseBean;
import com.guvi.gt.lataxi.model.BasicBean;

public interface BasicListener {

    void onLoadCompleted(BasicBean basicBean);

    void onLoadFailed(String error);

}
