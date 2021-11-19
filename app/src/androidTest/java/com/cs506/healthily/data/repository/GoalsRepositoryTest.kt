package com.cs506.healthily.data.repository

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
        }.addOnFailureListener{

        }
    }

    /**
     * Test the function getStepGoal() in GoalsRepository
     *
     */
    @Test
    fun getStepGoalTest() {

        assertThat(repository.getStepGoal()).isEqualTo("50")

    }

    /**
     * Test the function getHeartGoal() in GoalsRepository
     *
     */
    @Test
    fun getHeartGoalTest() {

        assertThat(repository.getHeartGoal()).isEqualTo("90")

    }

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