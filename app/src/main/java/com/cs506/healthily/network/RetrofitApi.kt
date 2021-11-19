package com.cs506.healthily.network

import com.cs506.healthily.models.googlePlaceModel.GoogleResponseModel
import com.cs506.healthily.models.googlePlaceModel.directionPlaceModel.DirectionResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitApi {

    @GET
    suspend fun getNearByPlaces(@Url url: String): Response<GoogleResponseModel>

    @GET
    suspend fun getDirection(@Url url: String): Response<DirectionResponseModel>
}