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

    private fun tearDown(){
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
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child(userId).child("gender").value).isEqualTo(gender)
            database.child("Users/$userId").removeValue()
          //  tearDown()
        }.addOnFailureListener{

        }
    }


    /*








     */



}

class setAge{
    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private fun tearDown(){
        database.child("Users/$userId").removeValue()
    }

    /**
     * Test the function setAgeTest() in UserSettingsRepository
     *
     */
    @Test
    fun setAgeTest() {
        val age : String = "23"
        repository.setAge(age)
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child(userId).child("age").value).isEqualTo(age)
            database.child("Users/$userId").removeValue()
           // tearDown()
        }.addOnFailureListener{

        }
    }
}

class setHeight{
    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private fun tearDown(){
        database.child("Users/$userId").removeValue()
    }
    /**
     * Test the function setHeightTest() in UserSettingsRepository
     *
     */
    @Test
    fun setHeightTest() {
        val height : String = "180"
        repository.setHeight(height)
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child(userId).child("height").value).isEqualTo(height)
            database.child("Users/$userId").removeValue()
           // tearDown()
        }.addOnFailureListener{

        }
    }
}

class setWeight{
    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private fun tearDown(){
        database.child("Users/$userId").removeValue()
    }
    /**
     * Test the function setWeightTest() in UserSettingsRepository
     *
     */
    @Test
    fun setWeightTest() {
        val weight : String = "60"
        repository.setWeight(weight)
        Thread.sleep(2000)
        database.child("Users").get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child(userId).child("weight").value).isEqualTo(weight)
            database.child("Users/$userId").removeValue()
           // tearDown()
        }.addOnFailureListener{

        }
    }

}

class getUserSettings {
    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private fun tearDown(){
        database.child("Users/$userId").removeValue()
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



        Thread.sleep(2000)
        repository.getUserSettings()?.observeForever{ mData ->
            Thread.sleep(2000)
            assertEquals("23", mData.age)
           // assertEquals("male", mData.gender)
          //  assertEquals("180", mData.height)
           // assertEquals("60", mData.weight)
            database.child("Users/$userId").removeValue()
           // tearDown()
        }









    }
}