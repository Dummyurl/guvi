package com.guvi.gt.lataxi.model;


public class LocationBean extends BaseBean {

    private String homeLocation;
    private String workLocation;
    private String homeLatitude;
    private String homeLongitude;
    private String workLatitude;
    private String workLongitude;

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getHomeLatitude() {
        return homeLatitude;
    }

    public void setHomeLatitude(String homeLatitude) {
        this.homeLatitude = homeLatitude;
    }

    public String getHomeLongitude() {
        return homeLongitude;
    }

    public void setHomeLongitude(String homeLongitude) {
        this.homeLongitude = homeLongitude;
    }

    public String getWorkLatitude() {
        return workLatitude;
    }

    public void setWorkLatitude(String workLatitude) {
        this.workLatitude = workLatitude;
    }

    public String getWorkLongitude() {
        return workLongitude;
    }

    public void setWorkLongitude(String workLongitude) {
        this.workLongitude = workLongitude;
    }
    
}
