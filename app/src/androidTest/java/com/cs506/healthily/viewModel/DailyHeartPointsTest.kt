package com.cs506.healthily.viewModel

import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DayHeart
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DailyHeartPointsTest {

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
                Assert.assertEquals("40", hp)
                database.child("Users/$userId/dailyHeartPoints/fakeDay").removeValue()
            }.addOnFailureListener{

            }
        }
    }

    @Test
    fun deleteDailyHeartPoints(){
        viewModel.deleteDailyHeartPoints()

        database.child("Users/$userId").get().addOnSuccessListener{
            Assert.assertFalse(it.child("dailyHeartPoints").exists())

        }.addOnFailureListener{

        }

    }

    @Test
    fun testGetAllDays(){

        // TODO: figure out how to test mutable live data


    }
}