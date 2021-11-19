package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DaySteps
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

    /**
     * Test the function addDailySteps(day: DaySteps) in DailStepsRepository
     *
     */
    @Test
    fun addDailyStepsTest() {
        val day : DaySteps = DaySteps("10", "100", "1000")

        repository.addDailySteps(day)

        database.child("Users").get().addOnSuccessListener {
            Truth.assertThat(it.child(user!!).child("dailySteps").value).isEqualTo(day.steps)
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

        val mLiveData: MutableLiveData<List<DaySteps>>? = repository.getDailySteps()

        // TODO()
    }
}