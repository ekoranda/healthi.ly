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

class JournalViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = JournalViewModel(api)

    @Test
    fun testJournal() {


        database.child("Users/$user/dailyActivity/fakeActivity/activity").setValue("fakeActivity")


        viewModel.getAllActivities()?.observeForever {mList ->

            database.child("Users/$user/dailyActivity").get().addOnSuccessListener {
                var repAct = "-1"
                for (child in it.children){
                    if(child.value == "fakeActivity"){
                        repAct = child.child("activity").value.toString()
                        Assert.assertEquals("fakeActivity", repAct)
                    }
                }


                database.child("Users/$user/dailyActivity/fakeActivity").removeValue()
            }



        }


    }
}