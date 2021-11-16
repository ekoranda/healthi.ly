package com.cs506.healthily.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DaySteps
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DailStepsRepository {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    fun getDailySteps(): MutableLiveData<List<DaySteps>>? {
        val mLiveData: MutableLiveData<List<DaySteps>> = MutableLiveData()
        val data = ArrayList<DaySteps>()
        val userRef = Firebase.database.getReference("Users/$user/dailySteps")
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot!!.children
                children.forEach{
                    val day: DaySteps = DaySteps()
                    day.day = it.key.toString()
                    day.steps = it.value.toString()
                    data.add(day)

                }



                mLiveData.postValue(data)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })






        return mLiveData
    }
}