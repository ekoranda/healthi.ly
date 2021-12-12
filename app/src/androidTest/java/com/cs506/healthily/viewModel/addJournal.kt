package com.cs506.healthily.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.JournalActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class addJournal {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = JournalViewModel(api)

    @Test
    fun testAddJournal(){
        val activity: JournalActivity = JournalActivity()
        activity.stepCount = "3000"
        activity.activity = "Test Activity"
        activity.date = "Test Date"
        activity.heartPoints = "40"
        viewModel.addJournalActivity(activity)

        database.child("Users/$user/dailyActivity").get().addOnSuccessListener{
            Assert.assertTrue(it.child("Test Date").exists())
            database.child("Users/$user/dailyActivity/Test Date").removeValue()



        }.addOnFailureListener{

        }
    }
}


