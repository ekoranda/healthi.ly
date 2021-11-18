package com.cs506.healthily.data.repository

import android.util.Log
import com.cs506.healthily.network.RetrofitClient
import com.cs506.healthily.utility.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MapRepository {

    fun getPlaces(url: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading(true))
        val response = RetrofitClient.retrofitApi.getNearByPlaces(url = url)

        Log.d("TAG", "getPlaces:  $response ")
        if (response.body()?.googlePlaceModelList?.size!! > 0) {
            Log.d(
                "TAG",
                "getPlaces:  Success called ${response.body()?.googlePlaceModelList?.size}"
            )

            emit(State.success(response.body()!!))
        } else {
            Log.d("TAG", "getPlaces:  failed called")
            emit(State.failed(response.body()!!.error!!))
        }


    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}