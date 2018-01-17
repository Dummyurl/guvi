package com.guvi.gt.lataxi.model;


import java.util.List;

public class TripListBean extends BaseBean {

    private PaginationBean pagination;
    private List<TripBean> trips;

    public PaginationBean getPagination() {

        return pagination;
    }

    public void setPagination(PaginationBean pagination) {

        this.pagination = pagination;
    }

    public List<TripBean> getTrips() {
        return trips;
    }

    public void setTrips(List<TripBean> trips) {
        this.trips = trips;
    }

    public static TripBean get(int position) {

        return null;

    }
}
