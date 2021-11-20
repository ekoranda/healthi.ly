package com.cs506.healthily.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.common.truth.Truth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Test

class DailStepsRepositoryTest{

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    val repository : DailStepsRepository = DailStepsRepository()

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = DayStepsViewModel(api)

    /**
     * Test the function addDailySteps(day: DaySteps) in DailStepsRepository
     *
     */


    @Test
    fun addDailyStepsTest() {
        val day : DaySteps = DaySteps("10", "100", "1000")

        repository.addDailySteps(day)

        database.child("Users").get().addOnSuccessListener {
            Truth.assertThat(it.child(user!!).child("dailySteps/10").value).isEqualTo(day.steps)
            database.child("Users/$user/dailySteps/10").removeValue()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function getDailySteps(): MutableLiveData<List<DaySteps>>? in DailStepsRepository
     *
     */
    @Test
    fun getDailyStepsTest() {
        val day : DaySteps = DaySteps("10", "100", "1000")






       // val mLiveData: MutableLiveData<List<DaySteps>>? = repository.getDailySteps()

        // TODO()
    }

    @Test
    fun test_removeDailyStep(){


        repository.deleteDailySteps()

        database.child("Users").get().addOnSuccessListener {
            assertFalse(it.child("$user/dailySteps").exists())
        }
    }
}