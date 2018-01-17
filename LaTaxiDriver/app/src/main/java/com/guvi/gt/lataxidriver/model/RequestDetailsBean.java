package com.guvi.gt.lataxidriver.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Jemsheer K D on 08 June, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class RequestDetailsBean extends BaseBean {

    private String requestID;
    private String carType;
    private String distance;
    private String eta;
    private String carTypeImage;
    private String customerID;
    private String customerName;
    private String customerPhoto;
    private String customerLocation;
    private String customerLatitude;
    private String customerLongitude;


    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getCarTypeImage() {
        return carTypeImage;
    }

    public void setCarTypeImage(String carTypeImage) {
        this.carTypeImage = carTypeImage;
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

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getCustomerLatitude() {
        return customerLatitude;
    }

    public void setCustomerLatitude(String customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public String getCustomerLongitude() {
        return customerLongitude;
    }

    public void setCustomerLongitude(String customerLongitude) {
        this.customerLongitude = customerLongitude;
    }


    public LatLng getDestinationLatLng() {
        return new LatLng(getDDestinationLatitude(), getDDestinationLongitude());
    }


    public double getDDestinationLatitude() {
        try {
            return Double.parseDouble(customerLatitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    public double getDDestinationLongitude() {
        try {
            return Double.parseDouble(customerLongitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
