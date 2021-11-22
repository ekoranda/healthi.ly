package com.cs506.healthily.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.data.model.UserSettings
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class goalViewModelTest{

    private lateinit var viewModel: goalViewModel

    private val database = Firebase.database.reference
    val userId = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

    @Before
    fun setup() {
        viewModel = goalViewModel(ApplicationProvider.getApplicationContext())


    }

    /**
     * Test the function getAge(userId: String): String? of goalViewModel .
     *
     */
    @Test
    fun getAgeOfgoalViewModel(){
        val age : String? = viewModel.getAge(userId!!)

        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                assertThat(it.child("age").value).isEqualTo(age)
            }.addOnFailureListener{

            }
        }

    }

    /**
     * Test the function getUserSettings(): MutableLiveData<UserSettings>? of goalViewModel .
     *
     */
    @Test
    fun getUserSettingsOfgoalViewModel(){
        val userSettings : MutableLiveData<UserSettings>? = viewModel.getUserSettings()
        val user : UserSettings? = userSettings?.value

        var pass = false

        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                if (user != null) {
                    if(it.child("age").value ==user.age && it.child("gender").value == user.gender
                        && it.child("height").value == user.height && it.child("weight").value == user.weight){
                        pass = true
                    }
                }
            }.addOnFailureListener{

            }

            assertThat(pass).isTrue()
        }

    }

    /**
     * Test the function setStepGoal(stepGoal : String) of goalViewModel .
     *
     */
    @Test
    fun setStepGoalOfgoalViewModel(){
        val stepGoal : String = "50"
        viewModel.setStepGoal(stepGoal)



        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                assertThat(it.child("stepGoal").value).isEqualTo("50")
            }.addOnFailureListener{

            }
        }

    }

    /**
     * Test the function setStepGoal(stepGoal : String) of goalViewModel .
     * If stepGoal = "hello", the function will not work and throw an exception there.
     *
     */
    @Test
    fun setStepGoalOfgoalViewModel1(){
        val stepGoal : String = "hello"
        viewModel.setStepGoal(stepGoal)



        if (userId != null) {
            database.child("Users").child(userId).get().addOnSuccessListener {
                assertThat(it.hasChild("stepGoal")).isFalse()
            }.addOnFailureListener{

            }
        }

    }


}