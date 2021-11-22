package com.cs506.healthily.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.DaySteps
import com.google.common.truth.Truth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DailyHeartsRepositoryTest {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    val repository : DailyHeartPointsRepository = DailyHeartPointsRepository()

    @Test
    fun test_addDailyHP() {
        val day : DayHeart = DayHeart()
        day.day = "fakeDay"
        day.heartPoints = "50"

        repository.addHeartPointsDay(day)

        database.child("Users").get().addOnSuccessListener {
            val hp = it.child("$user/dailyHeartPoints/fakeDay").value
            assertEquals("50", hp)
            database.child("Users/$user/dailyHeartPoints/fakeDay").removeValue()
        }.addOnFailureListener{

        }
    }

    @Test
    fun test_deleteDailyHP(){
        repository.deleteDailyHeartPoints()


        database.child("Users").get().addOnSuccessListener {
            assertFalse(it.child("$user/dailyHeartPoints").exists())
        }
    }

    @Test
    fun test_getDailyHeartPoints(){
        database.child("Users/$user/dailyHeartPoints/testDay").setValue("50")

        repository.getDailyHeartPoints()?.observeForever{mList ->
            for (goal in mList){
                if(goal.day == "testDay"){
                    assertEquals("50", goal.heartPoints)
                    database.child("Users/$user/dailyHeartPoints/testDay").removeValue()
                }
            }
        }
    }

}

