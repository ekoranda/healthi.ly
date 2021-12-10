package com.cs506.healthily.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class deleteJournal {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = JournalViewModel(api)


    @Test
    fun testDeleteJournal(){
        viewModel.deleteJournal()

        database.child("Users/$user").get().addOnSuccessListener{
            Thread.sleep(2000)
            Assert.assertFalse(it.child("dailyActivity").exists())

        }.addOnFailureListener{

        }
    }
}