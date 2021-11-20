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
    @Rule @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

   val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = JournalViewModel(api)

    @Test
    fun testJournal() {


        database.child("Users/$user/dailyActivity/fakeActivity/activity").setValue("fakeActivity")


        viewModel.getAllActivities()?.observeForever {mList ->

            database.child("Users").get().addOnSuccessListener {
                val size = mList.size
                val repAct = mList[1].activity
                assertEquals("fakeActivity", repAct)
                database.child("Users/$user/dailyActivity/fakeActivity").removeValue()
            }



        }


    }



}