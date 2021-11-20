package com.cs506.healthily.data.repository

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.cs506.healthily.viewModel.AboutYou
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.rules.TestRule

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class JournalActivityRepositoryTest {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val repository : JournalActivityRepository = JournalActivityRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun testGetAllActivities(){
        database.child("Users/$user/dailyActivity/fakeActivity/activity").setValue("fakeActivity")
        database.child("Users/$user/dailyActivity/fakeActivity/stepCount").setValue("1234")

        repository.getAllActivities()?.observeForever{ mList ->
            for(act in mList){
                if(act.activity == "fakeActivity"){
                    assertEquals("1234", act.stepCount)
                    database.child("Users/$user/dailyActivity/fakeActivity").removeValue()
                }
            }

        }

    }






}