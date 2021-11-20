package com.cs506.healthily.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.UserSettings
import com.google.common.truth.Truth.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UserSettingsRepositoryTest{


    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    fun tearDown(){
        database.child("Users/$userId").removeValue()
    }

    /**
     * Test the function setGenderTest() in UserSettingsRepository
     *
     */
    @Test
    fun setGenderTest() {
        val gender : String = "male"
        repository.setGender(gender)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("gender").value).isEqualTo(gender)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function setAgeTest() in UserSettingsRepository
     *
     */
    @Test
    fun setAgeTest() {
        val age : String = "23"
        repository.setAge(age)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("age").value).isEqualTo(age)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function setHeightTest() in UserSettingsRepository
     *
     */
    @Test
    fun setHeightTest() {
        val height : String = "180"
        repository.setHeight(height)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("height").value).isEqualTo(height)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function setWeightTest() in UserSettingsRepository
     *
     */
    @Test
    fun setWeightTest() {
        val weight : String = "60"
        repository.setWeight(weight)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("weight").value).isEqualTo(weight)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function getUserSettingsTest() in UserSettingsRepository
     *
     */
    @Test
    fun getUserSettingsTest() {



        database.child("Users/$userId/age").setValue("23")
        database.child("Users/$userId/gender").setValue("male")
        database.child("Users/$userId/height").setValue("180")
        database.child("Users/$userId/weight").setValue("60")




        repository.getUserSettings()?.observeForever{ mData ->
            assertEquals("23", mData.age)
            assertEquals("male", mData.gender)
            assertEquals("180", mData.height)
            assertEquals("60", mData.weight)
            tearDown()
        }









    }



}