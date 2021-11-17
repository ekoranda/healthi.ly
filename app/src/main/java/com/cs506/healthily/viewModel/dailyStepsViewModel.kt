package com.cs506.healthily.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.repository.DailStepsRepository

class DayStepsViewModel(application: Application) : AndroidViewModel(application) {
    //TODO: make repository
    private val repo: DailStepsRepository

    //TODO: make data class



    init {
       repo = DailStepsRepository()
    }

    fun getAllDays(): MutableLiveData<List<DaySteps>>? {
        return repo.getDailySteps()
    }

    fun addDay(day: DaySteps) = repo.addDailySteps(day)
}