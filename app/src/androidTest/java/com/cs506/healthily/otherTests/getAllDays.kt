package com.cs506.healthily.otherTests

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.viewModel.AboutYou
import com.cs506.healthily.viewModel.DailyHeartPointsViewModel
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/*

class GetAllDays{

    private lateinit var viewModel: DayStepsViewModel
    private val userId = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DayStepsViewModel(ApplicationProvider.getApplicationContext())
    }


    @Test
    fun testGetAllDays(){
        database.child("Users/$userId/dailySteps/fakeDay").setValue("1234")


        Thread.sleep(200000)
        viewModel.getAllDays()?.observeForever{ mList ->
            var repSteps = "-1"
            for (step in mList){
                Thread.sleep(200000)
                if(step.day == "fakeDay"){
                    Thread.sleep(200000)
                    Assert.assertEquals("1234", step.steps.toString())


                }
            }


        }



    }





}


class testAvailability {
    private lateinit var viewModel: AboutYou
    private val database = Firebase.database.reference
    val userId = "testingAccount"

    @Test
    fun setAvailEnd(){
        viewModel = AboutYou(ApplicationProvider.getApplicationContext())
        viewModel.setAvailabilityStartFromRepo(userId, "6pm")
        database.child("Users").child(userId).get().addOnSuccessListener {
            val startTime = it.child("availabilityStar").value
            Thread.sleep(200000)
            Assert.assertEquals("6pm", startTime)
            database.child("Users/testingAccount").removeValue()

        }
    }


}

class addDay {

    private lateinit var viewModel: DayStepsViewModel
    private val userId = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DayStepsViewModel(ApplicationProvider.getApplicationContext())
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
                Thread.sleep(200000)
                assertEquals("1000", steps)
                database.child("Users/$userId/dailySteps/fakeDay").removeValue()
            }.addOnFailureListener{

            }
        }
    }



}

 */










