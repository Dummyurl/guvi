package com.guvi.gt.lataxidriver.model;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class RatingDetailsBean extends BaseBean {

    private static final String TAG = "RatingDB";
    private int totalRating;
    private float averageRatings;
    private int totalRequests;
    private int requestsAccepted;
    private int totalTrips;
    private int tripsCancelled;

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public float getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(float averageRatings) {
        this.averageRatings = averageRatings;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getRequestsAccepted() {
        return requestsAccepted;
    }

    public void setRequestsAccepted(int requestsAccepted) {
        this.requestsAccepted = requestsAccepted;
    }

    public float getRequestAcceptedPercentage() {
        return totalRequests == 0 ? 0 : ((requestsAccepted * 100f) / totalRequests);
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(int totalTrips) {
        this.totalTrips = totalTrips;
    }

    public int getTripsCancelled() {
        return tripsCancelled;
    }

    public void setTripsCancelled(int tripsCancelled) {
        this.tripsCancelled = tripsCancelled;
    }

    public float getTripsCancelledPercentage() {
        return totalTrips == 0 ? 0 : ((tripsCancelled * 100f) / totalTrips);
    }
}
