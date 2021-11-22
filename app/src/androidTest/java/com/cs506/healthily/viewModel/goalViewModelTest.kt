package com.cs506.healthily.viewModel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class goalViewModelTest{

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: goalViewModel

    private val database = Firebase.database.reference
    private val userId = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

    @Before
    fun setup() {
        viewModel = goalViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testGetUserSettings(){
        database.child("Users/$userId/age").get().addOnSuccessListener {
            val currAge = it.value.toString()
            viewModel.getUserSettings()?.observeForever { mData ->
                assertEquals(currAge, mData.age.toString())

            }

        }
    }

    @Test
    fun testSetStepGoal(){
        viewModel.setStepGoal("8000")
        database.child("Users/$userId/stepGoal").get().addOnSuccessListener {
            assertEquals("8000", it.value)
        }

    }

    @Test
    fun testSetHeartGoal(){
        viewModel.setHeartGoal("50")
        database.child("Users/$userId/heartGoal").get().addOnSuccessListener {
            assertEquals("50", it.value)
        }
    }
}