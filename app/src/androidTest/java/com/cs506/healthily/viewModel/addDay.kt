package com.cs506.healthily.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DaySteps
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

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
                Thread.sleep(2000)
                assertEquals("1000", steps)
                database.child("Users/$userId/dailySteps/fakeDay").removeValue()
            }.addOnFailureListener{

            }
        }
    }

}

class deleteSteps {

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
    fun testDeleteSteps(){

        viewModel.deleteDailySteps()

        database.child("Users/$userId").get().addOnSuccessListener{
            Thread.sleep(2000)
            assertFalse(it.child("dailySteps").exists())

        }.addOnFailureListener{

        }




    }


}

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


        Thread.sleep(2000)
        viewModel.getAllDays()?.observeForever{ mList ->
            var repSteps = "-1"
            for (step in mList){
                Thread.sleep(2000)
                    if(step.day == "fakeDay"){
                        Thread.sleep(2000)
                        assertEquals("1234", step.steps.toString())


                }
            }


        }



    }



}