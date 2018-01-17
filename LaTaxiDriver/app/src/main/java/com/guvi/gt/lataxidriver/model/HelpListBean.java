package com.guvi.gt.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class HelpListBean extends BaseBean {

    private List<HelpBean> helpList;

    public List<HelpBean> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpBean> helpList) {
        this.helpList = helpList;
    }
}
