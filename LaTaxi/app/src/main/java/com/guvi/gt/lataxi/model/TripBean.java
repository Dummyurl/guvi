package com.guvi.gt.lataxi.model;


import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class TripBean extends BaseBean implements Comparable {

    private String id;
    private String tripStatus;
    private String date;
    private String carName;
    private long time;
    private String rate;
    private String driverPhoto;
    private String sourceLatitude;
    private String sourceLongitude;
    private String destinationLatitude;
    private String destinationLongitude;
    private LatLng sourceLatLng;
    private LatLng destinationLatLng;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
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
