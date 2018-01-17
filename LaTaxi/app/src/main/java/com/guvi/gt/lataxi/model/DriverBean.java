package com.guvi.gt.lataxi.model;


import com.google.android.gms.maps.model.LatLng;

public class DriverBean extends BaseBean {

    private String tripID;
    private int appStatus;
    private String requestStatus;
    private String driverName;
    private String driverPhoto;
    private String driverNumber;
    private float rating;
    private String carName;
    private String carNumber;
    private String time;
    private String carPhoto;
    private String sourceLatitude;
    private String sourceLongitude;
    private LatLng sourceLatLng;
    private String destinationLatitude;
    private String destinationLongitude;
    private LatLng destinationLatLng;
    private String carLatitude;
    private String carLongitude;
    private LatLng carLatLng;

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String drivetNumber) {
        this.driverNumber = drivetNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float v, float rating) {
        this.rating = rating;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getCarLatitude() {
        return carLatitude;
    }

    public void setCarLatitude(String carLatitude) {
        this.carLatitude = carLatitude;
    }

    public String getCarLongitude() {
        return carLongitude;
    }

    public void setCarLongitude(String carLongitude) {
        this.carLongitude = carLongitude;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
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


    public LatLng getCarLatLng() {
        carLatLng = new LatLng(getDCarLatitude(), getDCarLongitude());
        return carLatLng;
    }

    public void setCarLatLng(LatLng carLatLng) {
        this.carLatLng = carLatLng;
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


    public double getDCarLatitude() {
        try {
            return Double.parseDouble(carLatitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    public double getDCarLongitude() {
        try {
            return Double.parseDouble(carLongitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

}
