package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.UserSettings
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class UserSettingsRepository(userId : String) {
    val database = Firebase.database.reference
    var userId: String = userId

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


}