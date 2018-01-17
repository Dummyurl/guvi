package com.guvi.gt.lataxidriver.model


import com.google.android.gms.maps.model.LatLng
//import com.twitter.sdk.android.core.models.Place

class PlaceBean : BaseBean(), Comparable<PlaceBean> {

    //    10.015861  76.341867  10.107570  76.345662

    var id: Int = 0
    var address: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var name: String? = null
//    var place: Place? = null
    var latLng: LatLng? = null

    val dLatitude: Double
        get() {
            try {
                return java.lang.Double.parseDouble(latitude)
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                return 0.0
            }

        }


    val dLongitude: Double
        get() {
            try {
                return java.lang.Double.parseDouble(longitude)
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                return 0.0
            }

        }


    override fun compareTo(other: PlaceBean): Int {
        val bean = other
        if (id == bean.id)
            return 0
        else if (id > bean.id)
            return 1
        else
            return -1
    }
}
