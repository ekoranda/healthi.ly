package com.cs506.healthily.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.cs506.healthily.data.repository.UserSettingsRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AboutYou(application: Application) : AndroidViewModel(application) {


    fun setGenderFromRepo(userId: String, gender: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setGender(gender)
    }

    fun setAgeFromRepo(userId: String, age: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setAge(age)
    }

    fun setWeightFromRepo(userId: String, weight: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setWeight(weight)
    }

    fun setHeightFromRepo(userId: String, height: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setHeight(height)
    }

}