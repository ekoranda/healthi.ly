package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.DaySteps
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DailyHeartPointsRepository {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid


    fun getDailyHeartPoints(): MutableLiveData<List<DayHeart>>? {
        val mLiveData: MutableLiveData<List<DayHeart>> = MutableLiveData()
        val data = ArrayList<DayHeart>()
        val userRef = Firebase.database.getReference("Users/$user")
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stepGoal = snapshot.child("heartGoal").value.toString()

                val children = snapshot!!.child("dailyHeartPoints").children
                children.forEach{
                    val day: DayHeart = DayHeart()
                    day.day = it.key.toString()
                    day.heartPoints = it.value.toString()
                    day.heartGoal = stepGoal


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