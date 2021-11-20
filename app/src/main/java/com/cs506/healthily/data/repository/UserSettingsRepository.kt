package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.UserSettings
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserSettingsRepository(var userId: String) {
    private val database = Firebase.database.reference

    fun setGender(gender : String){
        database.child("Users").child(userId).child("gender").setValue(gender)
    }

    fun setAge(age : String){
        database.child("Users").child(userId).child("age").setValue(age)
    }

    fun setHeight(height : String){
        database.child("Users").child(userId).child("height").setValue(height)
    }

    fun setWeight(weight : String){
        database.child("Users").child(userId).child("weight").setValue(weight)
    }
    fun setAvailabilityStart(start : String){
        database.child("Users").child(userId).child("availabilityStart").setValue(start)
    }
    fun setAvailabilityEnd(end : String){
        database.child("Users").child(userId).child("availabilityEnd").setValue(end)
    }

    fun getUserSettings(): MutableLiveData<UserSettings> {
        val userSettingsObject: MutableLiveData<UserSettings> = MutableLiveData()
        val data = UserSettings()
        val userRef = Firebase.database.getReference("Users/${userId}")
        userRef.get().addOnSuccessListener {
            data.age = it.child("age").value.toString()
            data.gender = it.child("gender").value.toString()
            data.height = it.child("height").value.toString()
            data.weight = it.child("weight").value.toString()
            data.availabilityStart = it.child("availabilityStart").value.toString()
            data.availabilityEnd = it.child("availabilityEnd").value.toString()
            userSettingsObject.postValue(data)
        }.addOnFailureListener{

        }

        return userSettingsObject
    }

    fun getAge(): String? {
        var age: String? = null
        val userRef = Firebase.database.getReference("Users/${userId}")
        userRef.get().addOnSuccessListener {
            age = it.child("age").value.toString()
            age = "23"

        }.addOnFailureListener{
            age = "22"
        }

        return age
    }


}