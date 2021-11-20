package com.cs506.healthily.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs506.healthily.models.googlePlaceModel.GooglePlaceModel
import com.cs506.healthily.data.repository.MapRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class LocationViewModel : ViewModel() {

    private val repo = MapRepository()

    fun getNearByPlace(url: String) = repo.getPlaces(url)


    fun getDirection(url: String) = repo.getDirection(url)

   // fun getUserLocations() = repo.getUserLocations()

}