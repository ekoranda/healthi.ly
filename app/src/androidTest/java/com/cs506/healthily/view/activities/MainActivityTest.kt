package com.cs506.healthily.view.activities

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val userId = Firebase.auth.currentUser?.uid
    private val database = Firebase.database.reference



    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testDeleteJournal() {
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
               activity.deleteJournal()
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {
                    assertFalse(it.child("dailyActivity").exists())
                }.addOnFailureListener{

                }
            }
        }
    }

    /*

    @Test
    fun testGetActivitiies(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            activity.getActivities()
            if (userId != null) {
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertFalse(it.child("dailyActivity").exists())
                }.addOnFailureListener{

                }
            }

        }

    }

     */
}


