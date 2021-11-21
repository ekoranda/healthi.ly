package com.cs506.healthily.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cs506.healthily.data.repository.UserSettingsRepository


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
    fun setAvailabilityStartFromRepo(userId: String, start: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setAvailabilityStart(start)
    }
    fun setAvailabilityEndFromRepo(userId: String, end: String){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        repository.setAvailabilityEnd(end)
    }

}