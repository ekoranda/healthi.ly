package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.Goals
import com.cs506.healthily.data.model.UserSettings
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class GoalsRepository(var userId: String) {
    private val database = Firebase.database.reference

    fun setStepsGoal(stepGoal : String){
        database.child("Users").child(userId).child("stepGoal").setValue(stepGoal)
    }

    fun getStepGoal(): String? {
        var stepGoal: String? = null
        val userRef = Firebase.database.getReference("Users/${userId}")
        userRef.get().addOnSuccessListener {
            stepGoal = it.child("stepGoal").value.toString()
        }.addOnFailureListener{
        }
        return stepGoal
    }

    fun setHeartGoal(heartGoal : String){
        database.child("Users").child(userId).child("heartGoal").setValue(heartGoal)
    }

    fun getHeartGoal(): String? {
        var heartGoal: String? = null
        val userRef = Firebase.database.getReference("Users/${userId}")
        userRef.get().addOnSuccessListener {
            heartGoal = it.child("heartGoal").value.toString()

        }.addOnFailureListener{
        }
        return heartGoal
    }

    fun getGoals(): MutableLiveData<Goals> {
        val goalsObject: MutableLiveData<Goals> = MutableLiveData()
        val data = Goals()
        val userRef = Firebase.database.getReference("Users/${userId}")
        userRef.get().addOnSuccessListener {
            data.heartGoal = it.child("heartGoal").value.toString()
            data.stepGoal = it.child("stepGoal").value.toString()
            goalsObject.postValue(data)
        }.addOnFailureListener{

        }

        return goalsObject
    }

}