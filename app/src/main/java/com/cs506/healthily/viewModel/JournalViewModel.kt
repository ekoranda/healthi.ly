package com.cs506.healthily.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.data.model.UserSettings
import com.cs506.healthily.data.repository.DailStepsRepository
import com.cs506.healthily.data.repository.JournalActivityRepository

class JournalViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: JournalActivityRepository

    //TODO: make data class



    init {
        repo = JournalActivityRepository()
    }
    fun getAllActivities(): MutableLiveData<List<JournalActivity>>? {
        return repo.getAllActivities()
    }



}
