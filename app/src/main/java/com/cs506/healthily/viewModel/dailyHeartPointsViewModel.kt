package com.cs506.healthily.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.repository.DailStepsRepository
import com.cs506.healthily.data.repository.DailyHeartPointsRepository

class DailyHeartPointsViewModel(application: Application) : AndroidViewModel(application) {
    //TODO: make repository
    private val repo: DailyHeartPointsRepository

    //TODO: make data class



    init {
        repo = DailyHeartPointsRepository()
    }

    fun getAllDays(): MutableLiveData<List<DayHeart>>? {
        return repo.getDailyHeartPoints()
    }
}