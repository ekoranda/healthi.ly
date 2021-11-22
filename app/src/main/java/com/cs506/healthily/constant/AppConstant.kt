package com.cs506.healthily.constant

import com.cs506.healthily.R
import com.cs506.healthily.data.model.PlaceModel

class AppConstant {
    companion object {
        @JvmStatic
        val STORAGE_REQUEST_CODE = 1000

        const val LOCATION_REQUEST_CODE = 2000

        //These can be changed to include any place type available on google places API. These are displayed in home fragment
        @JvmStatic
        val placesName =
            listOf<PlaceModel>(
                PlaceModel(1, R.drawable.ic_gym, "Gyms", "gym"),
                PlaceModel(2, R.drawable.ic_tennis, "Parks", "park"),
                PlaceModel(3, R.drawable.ic_restaurant, "Restaurants", "restaurant"),
                PlaceModel(4, R.drawable.ic_camp, "Campground", "campground"),
                PlaceModel(5, R.drawable.ic_saloon, "Spas", "spa")
            )

    }
}