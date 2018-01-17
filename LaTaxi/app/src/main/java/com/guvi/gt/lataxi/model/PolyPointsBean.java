package com.guvi.gt.lataxi.model;


import java.util.HashMap;
import java.util.List;

public class PolyPointsBean extends BaseBean {

    private long time;
    private int distance;
    private String timeText;
    private String distanceText;

    List<List<HashMap<String, String>>> routes;


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public String getDistanceText() {
        return distanceText;
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public List<List<HashMap<String, String>>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<HashMap<String, String>>> routes) {
        this.routes = routes;
    }
}
