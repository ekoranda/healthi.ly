package com.cs506.healthily.viewModel

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.DayHeart
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule








class getAllDaysTestt{

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DailyHeartPointsViewModel
    private val userId = Firebase.auth.currentUser?.uid


    private val database = Firebase.database.reference

    @Test
    fun getDays() {
        database.child("Users/$userId/dailyHeartPoints/fakeDay").setValue("1234")
        viewModel = DailyHeartPointsViewModel(ApplicationProvider.getApplicationContext())
        viewModel.getAllDays()?.observeForever{ mList ->
            for (hp in mList){
                if (hp.day == "fakeDay"){
                    Thread.sleep(2000)
                    Assert.assertEquals("1234", hp.heartPoints.toString() )
                }

            }

        }


    }

}