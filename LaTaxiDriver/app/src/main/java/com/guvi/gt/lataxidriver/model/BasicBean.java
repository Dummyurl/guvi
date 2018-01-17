package com.guvi.gt.lataxidriver.model;

/**
 * Created by Jemsheer K D on 03 December, 2016.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxi
 */

public class BasicBean extends BaseBean {


    private String requestID;
    private String tripID;
    private int tripStatus;
    private String otpCode;
    private String phone;

    private int countryID;
    private int stateID;
    private int districtID;

    private boolean isDriverOnline;
    private boolean isPhoneAvailable;

   /* private List<CountryBean> countries;
    private List<StateBean> states;
    private List<DistrictBean> cities;
    private List<CourtBean> courts;*/

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public int getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(int tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPhoneAvailable() {
        return isPhoneAvailable;
    }

    public void setPhoneAvailable(boolean phoneAvailable) {
        isPhoneAvailable = phoneAvailable;
    }

    public boolean isDriverOnline() {
        return isDriverOnline;
    }

    public void setDriverOnline(boolean driverOnline) {
        isDriverOnline = driverOnline;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }
/*
    public List<CourtBean> getCourts() {
        return courts;
    }

    public void setCourts(List<CourtBean> courts) {
        this.courts = courts;
    }

    public List<CountryBean> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryBean> countries) {
        this.countries = countries;
    }

    public List<StateBean> getStates() {
        return states;
    }

    public void setStates(List<StateBean> states) {
        this.states = states;
    }

    public List<DistrictBean> getDistricts() {
        return cities;
    }

    public void setDistricts(List<DistrictBean> cities) {
        this.cities = cities;
    }*/
}
