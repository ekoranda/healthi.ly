package com.cs506.healthily.constant

import com.cs506.healthily.R
import com.cs506.healthily.data.model.PlaceModel

class AppConstant {
    companion object {
        //TODO: may not these days vals later...
        @JvmStatic
        val STORAGE_REQUEST_CODE = 1000

        const val LOCATION_REQUEST_CODE = 2000

        @JvmStatic
        val placesName =
            listOf<PlaceModel>(
                PlaceModel(1, R.drawable.ic_saloon, "Gyms", "gym"),
                PlaceModel(2, R.drawable.ic_saloon, "Bar", "bar"),
                PlaceModel(3, R.drawable.ic_pharmacy, "Pharmacies", "pharmacy"),
                PlaceModel(4, R.drawable.ic_hospital, "Hospitals & Clinics", "hospital"),
                PlaceModel(5, R.drawable.ic_saloon, "Beauty Salons", "beauty_salon")
            )

    }
}