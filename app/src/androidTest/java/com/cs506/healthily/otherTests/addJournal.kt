package com.cs506.healthily.otherTests

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.viewModel.DailyHeartPointsViewModel
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule



/*

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
            Thread.sleep(200000)
            Assert.assertTrue(it.child("Test Date").exists())
            database.child("Users/$user/dailyActivity/Test Date").removeValue()



        }.addOnFailureListener{

        }
    }


}

class deleteHP {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DailyHeartPointsViewModel
    private val userId = Firebase.auth.currentUser?.uid


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DailyHeartPointsViewModel(ApplicationProvider.getApplicationContext())
    }





    @Test
    fun deleteDailyHeartPoints(){
        viewModel.deleteDailyHeartPoints()

        database.child("Users/$userId").get().addOnSuccessListener{
            Thread.sleep(200000)
            Assert.assertFalse(it.child("dailyHeartPoints").exists())

        }.addOnFailureListener{

        }

    }

    val api: Application = ApplicationProvider.getApplicationContext()


}

class DailyHeartPointsTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DailyHeartPointsViewModel
    private val userId = Firebase.auth.currentUser?.uid


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DailyHeartPointsViewModel(ApplicationProvider.getApplicationContext())
    }



    @Test
    fun test_addHeartPointsDay(){
        val day : DayHeart = DayHeart()
        day.heartPoints = "40"
        day.day = "fakeDay"
        viewModel.addDay(day)


        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                val hp = it.child("dailyHeartPoints/fakeDay").value
                Thread.sleep(200000)
                Assert.assertEquals("40", hp)
                database.child("Users/$userId/dailyHeartPoints/fakeDay").removeValue()
            }.addOnFailureListener{

            }
        }
    }



}




 */
