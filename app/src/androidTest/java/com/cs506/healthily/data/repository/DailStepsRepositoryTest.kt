package com.cs506.healthily.data.repository

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DailStepsRepositoryTest {

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    val repository: DailStepsRepository = DailStepsRepository()

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = DayStepsViewModel(api)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    /**
     * Test the function addDailySteps(day: DaySteps) in DailStepsRepository
     *
     */



    @Test
    fun addDailyStepsTest() {
        val day: DaySteps = DaySteps("10", "100", "1000")

        repository.addDailySteps(day)
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            Truth.assertThat(it.child(user!!).child("dailySteps/10").value).isEqualTo(day.steps)
            database.child("Users/$user/dailySteps/10").removeValue()
        }.addOnFailureListener {

        }
    }

}

class getSteps{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    val repository : DailStepsRepository = DailStepsRepository()

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = DayStepsViewModel(api)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    /**
     * Test the function getDailySteps(): MutableLiveData<List<DaySteps>>? in DailStepsRepository
     *
     */
    @Test
    fun getDailyStepsTest() {
        database.child("Users/$user/dailySteps/testDay").setValue("5000")
        Thread.sleep(2000)
        repository.getDailySteps()?.observeForever{mList ->
            for (goal in mList){
                if(goal.day == "testDay"){
                    Thread.sleep(2000)
                    assertEquals("5000", goal.steps)
                    database.child("Users/$user/dailySteps/testDay").removeValue()
                }
            }
        }
    }
}

class removeDailySteps{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    val repository : DailStepsRepository = DailStepsRepository()

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = DayStepsViewModel(api)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun test_removeDailyStep(){


        repository.deleteDailySteps()
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertFalse(it.child("$user/dailySteps").exists())
        }
    }
}

