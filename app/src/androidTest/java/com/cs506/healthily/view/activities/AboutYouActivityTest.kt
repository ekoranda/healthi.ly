package com.cs506.healthily.view.activities

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.common.truth.Truth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AboutYouActivityTest {


    private val userId = Firebase.auth.currentUser?.uid
    private val database = Firebase.database.reference

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AboutYouActivity::class.java)
    @Test
    fun testEvent() {
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindGender(userId, "male")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("male", it.child("gender").value)

                }.addOnFailureListener{

                }
            }
        }
    }

    @Test
    fun testBindAge(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindAge(userId, "25")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("25", it.child("age").value)

                }.addOnFailureListener{

                }

            }

        }

    }

    @Test
    fun testBindHeight(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindHeight(userId, "4'11")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("4'11", it.child("height").value)

                }.addOnFailureListener{

                }

            }

        }

    }

    @Test
    fun testBindWeight(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindWeight(userId, "145")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("145", it.child("weight").value)

                }.addOnFailureListener{

                }

            }

        }

    }

    @Test
    fun testBindStartTime(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindAvailabilityStart(userId, "11am")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("11am", it.child("availabilityStart").value)

                }.addOnFailureListener{

                }

            }

        }

    }

    @Test
    fun testBindEndTime(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindAvailabilityEnd(userId, "2pm")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {

                    assertEquals("2pm", it.child("availabilityEnd").value)

                }.addOnFailureListener{

                }

            }

        }

    }



}