package com.cs506.healthily.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.UserSettings
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.data.repository.UserSettingsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class stepCountGoal(application: Application) : AndroidViewModel(application) {

    fun getAge(userId: String): String? {

        return "0"

    }

    private val userRepository: UserSettingsRepository
    private val goalsRepository : GoalsRepository
    val userId = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

    init {
        userRepository = userId?.let { UserSettingsRepository(it) }!!
        goalsRepository = userId?.let { GoalsRepository(it)} !!
    }




    fun getGoalOne(): MutableLiveData<UserSettings>? {
        return userRepository.getUserSettings()
    }

    fun setStepGoal(stepGoal : String) {
        goalsRepository.setStepsGoal(stepGoal)
    }










}