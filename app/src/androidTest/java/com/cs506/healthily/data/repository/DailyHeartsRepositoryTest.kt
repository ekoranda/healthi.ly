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
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            val hp = it.child("$user/dailyHeartPoints/fakeDay").value
            Thread.sleep(2000)
            assertEquals("50", hp)
            database.child("Users/$user/dailyHeartPoints/fakeDay").removeValue()
        }.addOnFailureListener{

        }
    }

    /*






     */

}

class deleteDailyHp{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    val repository : DailyHeartPointsRepository = DailyHeartPointsRepository()
    @Test
    fun test_deleteDailyHP(){
        repository.deleteDailyHeartPoints()

        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertFalse(it.child("$user/dailyHeartPoints").exists())
        }
    }

}

class getDailyHP{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    val repository : DailyHeartPointsRepository = DailyHeartPointsRepository()
    @Test
    fun test_getDailyHeartPoints(){
        database.child("Users/$user/dailyHeartPoints/testDay").setValue("50")
        Thread.sleep(2000)
        repository.getDailyHeartPoints()?.observeForever{mList ->
            for (goal in mList){
                Thread.sleep(2000)
                if(goal.day == "testDay"){
                    Thread.sleep(2000)
                    assertEquals("50", goal.heartPoints)
                    database.child("Users/$user/dailyHeartPoints/testDay").removeValue()
                }
            }
        }
    }

}

class testFirebase{
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()


    val repository : DailyHeartPointsRepository = DailyHeartPointsRepository()
    @Test
    fun test_firebase(){
        Thread.sleep(2000)
        assertEquals(user, repository.user)
    }
}

