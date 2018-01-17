package com.guvi.gt.lataxi.model;


import java.util.List;

public class LandingPageBean extends BaseBean {

    private List<CarBean> cars;

    public List<CarBean> getCars() {
        return cars;
    }

    public void setCars(List<CarBean> cars) {
        this.cars = cars;
    }

    public CarBean getCar(String carID) {
        if (cars != null) {
            for (CarBean car : cars) {

                if (car.getCarID().equalsIgnoreCase(carID)) {
                    return car;
                }
            }
        }
        return null;
    }


}
