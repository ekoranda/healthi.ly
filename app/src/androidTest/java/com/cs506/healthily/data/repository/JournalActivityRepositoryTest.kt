package com.cs506.healthily.data.repository

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.viewModel.AboutYou
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.rules.TestRule

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock




class deleteActivities{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val repository : JournalActivityRepository = JournalActivityRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun deleteActivities(){
        database.child("Users/$user/dailyActivity/fakeActivity/activity").setValue("fakeActivity")
        repository.deleteJournal()
        Thread.sleep(2000)
        database.child("Users/$user").get().addOnSuccessListener{
            Thread.sleep(2000)
            Assert.assertFalse(it.child("dailyActivity").exists())

        }.addOnFailureListener{

        }

    }
}

class addActivity {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val repository : JournalActivityRepository = JournalActivityRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun addActivity(){
        val activity: JournalActivity = JournalActivity()
        activity.stepCount = "3000"
        activity.activity = "Test Activity"
        activity.date = "Test Date"
        activity.heartPoints = "40"

        repository.addJournalActivity(activity)
        Thread.sleep(2000)
        database.child("Users/$user/dailyActivity").get().addOnSuccessListener{
            Thread.sleep(2000)
            Assert.assertTrue(it.child("Test Date").exists())
            database.child("Users/$user/dailyActivity/Test Date").removeValue()



        }.addOnFailureListener{

        }
    }
}

class addActivity2{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val repository : JournalActivityRepository = JournalActivityRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun addActivity2(){
        val activity: JournalActivity = JournalActivity()
        activity.stepCount = null
        activity.activity = "Test Activity"
        activity.date = "Test Date"
        activity.heartPoints = null
        repository.addJournalActivity(activity)
        Thread.sleep(2000)
        database.child("Users/$user/dailyActivity").get().addOnSuccessListener{
            Thread.sleep(2000)
            Assert.assertTrue(it.child("Test Date").exists())
            database.child("Users/$user/dailyActivity/Test Date").removeValue()



        }.addOnFailureListener{

        }

    }

}











