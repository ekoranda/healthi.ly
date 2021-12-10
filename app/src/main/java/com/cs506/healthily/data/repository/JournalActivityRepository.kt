package com.cs506.healthily.data.repository

import androidx.lifecycle.MutableLiveData
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.JournalActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class JournalActivityRepository {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    fun getAllActivities(): MutableLiveData<List<JournalActivity>>? {
        val mLiveData: MutableLiveData<List<JournalActivity>> = MutableLiveData()
        val data = ArrayList<JournalActivity>()

        val userRef = Firebase.database.getReference("Users/$user/dailyActivity")
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                val children = snapshot!!.children
                children.forEach{
                    val activity: JournalActivity = JournalActivity()
                    activity.heartPoints = it.child("heartPoints").value.toString()
                    activity.stepCount = it.child("stepCount").value.toString()
                    activity.activity = it.child("activity").value.toString()
                    activity.date = it.child("date").value.toString()


                    data.add(activity)

                }



                mLiveData.postValue(data)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })






        return mLiveData
    }

    fun addJournalActivity(activity: JournalActivity){
        val day = activity.date
        val hp = activity.heartPoints
        val activityType = activity.activity
        val stepCount = activity.stepCount
        if(stepCount != null){
            database.child("Users/$user/dailyActivity/$day/stepCount").setValue(stepCount)

        }
        if(hp != null){
            database.child("Users/$user/dailyActivity/$day/heartPoints").setValue(hp)

        }
        database.child("Users/$user/dailyActivity/$day/date").setValue(day)
        database.child("Users/$user/dailyActivity/$day/activity").setValue(activityType)

    }

    fun deleteJournal(){
        database.child("Users/$user/dailyActivity").removeValue()
    }
}