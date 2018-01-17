package com.guvi.gt.lataxi.model;


import android.location.Address;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import java.util.List;

public class SearchResultsBean extends BaseBean {

    private List<Place> place;

    public List<Place> getPlace() {
        return place;
    }

    public void setPlace(List<Place> place) {
        this.place = place;
    }

}
