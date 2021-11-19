package com.cs506.healthily.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.Goals
import com.cs506.healthily.data.model.UserSettings
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Test

class GoalsRepositoryTest{

    private val database = Firebase.database.reference
    val userId: String = "testUser"
    val repository : GoalsRepository = GoalsRepository(userId)

    private fun tearDown(){
        database.child("Users/$userId").removeValue()
    }

    /**
     * Test the function setStepGoal(stepGoal : String) in GoalsRepository
     *
     */
    @Test
    fun setStepGoalTest() {
        val stepGoal : String = "50"
        repository.setStepGoal(stepGoal)
        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("stepGoal").value).isEqualTo(stepGoal)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function setHeartGoal(heartGoal : String) in GoalsRepository
     *
     */
    @Test
    fun setHeartGoalTest() {
        val heartGoal : String = "90"
        repository.setHeartGoal(heartGoal)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.child(userId).child("heartGoal").value).isEqualTo(heartGoal)
            tearDown()
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function getStepGoal() in GoalsRepository
     *
     */
    /*
    @Test
    fun getStepGoalTest() {
        database.child("Users/$userId/stepGoal").setValue("50")
        assertThat(repository.getStepGoal()).isEqualTo("50")
        tearDown()

    }

     */

    /**
     * Test the function getHeartGoal() in GoalsRepository
     *
     */
    /*
    @Test
    fun getHeartGoalTest() {
        database.child("Users/$userId/heartGoal").setValue("90")
        assertThat(repository.getHeartGoal()).isEqualTo("90")
        tearDown()

    }

     */

    /**
     * Test the function getGoals() in GoalsRepository
     *
     */

    @Test
    fun getGoalsTest() {


        var bool = false
        var goalsObject: MutableLiveData<Goals> = MutableLiveData()

        goalsObject = repository.getGoals()

        val Goals = goalsObject.value


        if (Goals != null) {




            if(Goals.heartGoal == "90" && Goals.stepGoal == "50" ){
                bool = true
            }
        }

        assertThat(bool).isTrue()


    }


}