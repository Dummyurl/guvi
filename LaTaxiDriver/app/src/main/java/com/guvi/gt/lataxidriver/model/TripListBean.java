package com.guvi.gt.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 05 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class TripListBean extends BaseBean {

    private String totalFare;
    private String totalTimeOnline;
    private int totalRidesTaken;
    private List<TripBean> trips;
    private PaginationBean pagination;

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getTotalTimeOnline() {
        return totalTimeOnline;
    }

    public void setTotalTimeOnline(String totalTimeOnline) {
        this.totalTimeOnline = totalTimeOnline;
    }

    public int getTotalRidesTaken() {
        return totalRidesTaken;
    }

    public void setTotalRidesTaken(int totalRidesTaken) {
        this.totalRidesTaken = totalRidesTaken;
    }

    public List<TripBean> getTrips() {
        return trips;
    }

    public void setTrips(List<TripBean> trips) {
        this.trips = trips;
    }

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }
}
