package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.UserSettings
import com.google.common.truth.Truth.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Test

class UserSettingsRepositoryTest{


    private val database = Firebase.database.reference

    val userId: String = "testUser"
    val repository : UserSettingsRepository = UserSettingsRepository(userId)

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
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function getUserSettingsTest() in UserSettingsRepository
     *
     */
    @Test
    fun getUserSettingsTest() {
        var bool = false
        var userSettingsObject: MutableLiveData<UserSettings> = MutableLiveData()

        userSettingsObject = repository.getUserSettings()

        val TestUserSettings = userSettingsObject.value

        if (TestUserSettings != null) {
            if(TestUserSettings.age == "23" && TestUserSettings.gender == "male" && TestUserSettings.height == "180" && TestUserSettings.weight == "60"){
                bool = true
            }
        }

        assertThat(bool).isTrue()

    }

    /**
     * Test the function getUserSettingsTest() in UserSettingsRepository
     *
     */
    @Test
    fun getAgeTest() {
        var age : String

        age = repository.getAge().toString()


        assertThat(age).isEqualTo("23")

    }

}