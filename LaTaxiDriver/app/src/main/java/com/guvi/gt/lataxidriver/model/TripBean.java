package com.guvi.gt.lataxidriver.model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Jemsheer K D on 05 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class TripBean extends BaseBean implements Comparable {

    private String id;
    private String tripStatus;
    private String driverID;
    private String driverName;
    private String driverPhoto;
    private int driverStatus;
    private String customerID;
    private String customerName;
    private String customerPhoto;
    private String sourceLocation;
    private String sourceLatitude;
    private String sourceLongitude;
    private LatLng sourceLatLng;
    private String destinationLocation;
    private String destinationLatitude;
    private String destinationLongitude;
    private LatLng destinationLatLng;
    private long startTime;
    private long endTime;
    private String fare;
    private String fee;
    private String tax;
    private String estimatedPayout;
    private String duration;
    private String distance;
    private float rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public int getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(int driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
    }

    public String getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getEstimatedPayout() {
        return estimatedPayout;
    }

    public void setEstimatedPayout(String estimatedPayout) {
        this.estimatedPayout = estimatedPayout;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LatLng getSourceLatLng() {
        sourceLatLng = new LatLng(getDSourceLatitude(), getDSourceLongitude());
        return sourceLatLng;
    }

    public void setSourceLatLng(LatLng sourceLatLng) {
        this.sourceLatLng = sourceLatLng;
    }

    public LatLng getDestinationLatLng() {
        destinationLatLng = new LatLng(getDDestinationLatitude(), getDDestinationLongitude());
        return destinationLatLng;
    }

    public void setDestinationLatLng(LatLng destinationLatLng) {
        this.destinationLatLng = destinationLatLng;
    }


    public double getDSourceLatitude() {
        try {
            return Double.parseDouble(sourceLatitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    public double getDSourceLongitude() {
        try {
            return Double.parseDouble(sourceLongitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    public double getDDestinationLatitude() {
        try {
            return Double.parseDouble(destinationLatitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    public double getDDestinationLongitude() {
        try {
            return Double.parseDouble(destinationLongitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    @Override
    public int compareTo(@NonNull Object obj) {
        TripBean bean = (TripBean) obj;
        int comparison = id.compareTo(bean.getId());
        if (comparison == 0) {
            return 0;
        } else if (comparison > 0) {
            return 1;
        } else
            return -1;
    }
}
