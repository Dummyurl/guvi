package com.guvi.gt.lataxidriver.model;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class HelpBean extends BaseBean {

    private String id;
    private String icon;
    private String title;
    private String content;
    private boolean isHelpful;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHelpful() {
        return isHelpful;
    }

    public void setHelpful(boolean helpful) {
        isHelpful = helpful;
    }
}
