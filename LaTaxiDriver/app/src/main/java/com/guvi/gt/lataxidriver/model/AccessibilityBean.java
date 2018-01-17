package com.guvi.gt.lataxidriver.model;


public class AccessibilityBean extends BasicBean {

    private boolean isDeaf;
    private boolean isFlashRequired;

    public boolean isDeaf() {
        return isDeaf;
    }

    public void setDeaf(boolean deaf) {
        isDeaf = deaf;
    }

    public boolean isFlashRequired() {
        return isFlashRequired;
    }

    public void setFlashRequired(boolean flashRequired) {
        isFlashRequired = flashRequired;
    }
}
