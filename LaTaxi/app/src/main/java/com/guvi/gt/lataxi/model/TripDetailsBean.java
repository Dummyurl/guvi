package com.guvi.gt.lataxi.model;


import java.util.List;

public class TripDetailsBean extends BaseBean {

    private String ID;
    private String tripStatus;
    private String time;
    private float rating;
    private String driverName;
    private String driverPhoto;
    private String kilometer;
    private String minute;
    private String baseFare;
    private String kilometerFare;
    private String minutesFare;
    private String subTotalFare;
    private String promotionFare;
    private String totalFare;

    private String sourceLatitude;
    private String sourceLongitude;
    private String destinationLatitude;
    private String destinationLongitude;

    private String sourceName;
    private String destinationName;

    private List<PlaceBean> path;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getKilometerFare() {
        return kilometerFare;
    }

    public void setKilometerFare(String kilometerFare) {
        this.kilometerFare = kilometerFare;
    }

    public String getMinutesFare() {
        return minutesFare;
    }

    public void setMinutesFare(String minutesFare) {
        this.minutesFare = minutesFare;
    }

    public String getSubTotalFare() {
        return subTotalFare;
    }

    public void setSubTotalFare(String subTotal) {
        this.subTotalFare = subTotal;
    }

    public String getPromotionFare() {
        return promotionFare;
    }

    public void setPromotionFare(String promotionFare) {
        this.promotionFare = promotionFare;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
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

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public List<PlaceBean> getPath() {
        return path;
    }

    public void setPath(List<PlaceBean> path) {
        this.path = path;
    }
}
