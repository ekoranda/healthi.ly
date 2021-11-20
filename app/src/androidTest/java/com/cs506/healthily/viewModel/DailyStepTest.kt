package com.cs506.healthily.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.cs506.healthily.data.model.DaySteps
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse


import org.junit.Before
import org.junit.Test

class DailyStepTest {
    private lateinit var viewModel: DayStepsViewModel
    private val userId = Firebase.auth.currentUser?.uid


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DayStepsViewModel(getApplicationContext())
    }



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun testAddDay(){
        val day: DaySteps = DaySteps()
        day.steps = "1000"
        day.day = "fakeDay"
        viewModel.addDay(day)

        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                val steps = it.child("dailySteps/fakeDay").value
                assertEquals("1000", steps)
                database.child("Users/$userId/dailySteps/fakeDay").removeValue()
            }.addOnFailureListener{

            }
        }
    }

    var step1 = "0"
    var step2 = "0"
    var step3 = "0"
    var step4 = "0"
    var step5 = "0"
    var step6 = "0"
    var step7 = "0"

    @Test
    fun testDeleteSteps(){

        viewModel.deleteDailySteps()

        database.child("Users/$userId").get().addOnSuccessListener{
            assertFalse(it.child("dailySteps").exists())

        }.addOnFailureListener{

        }




    }

    @Test
    fun testGetAllDays(){

        // TODO: figure out how to test mutable live data


        }


    }



}