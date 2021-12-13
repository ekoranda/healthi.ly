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

