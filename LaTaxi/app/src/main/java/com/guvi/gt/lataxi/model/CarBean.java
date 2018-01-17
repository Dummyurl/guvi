package com.guvi.gt.lataxi.model;


import android.support.annotation.NonNull;

public class CarBean extends BaseBean implements Comparable<CarBean>{

    private String carID;
    private String carsAvailable;
    private String carName;
    private String carImage;
    private String minTime;
    private String minFare;
    private String maxSize;

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarsAvailable() {
        return carsAvailable;
    }

    public void setCarsAvailable(String carsAvailable) {
        this.carsAvailable = carsAvailable;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMinFare() {
        return minFare;
    }

    public void setMinFare(String minFare) {
        this.minFare = minFare;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public int compareTo(@NonNull CarBean bean) {
        int id = Integer.parseInt(carID);
        int tripID = Integer.parseInt(bean.getCarID());
        if (id == tripID) {
            return 0;
        } else if (id > tripID) {
            return 1;
        } else
            return -1;
    }
}
