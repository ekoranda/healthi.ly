package com.cs506.healthily

import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.cs506.healthily.view.activities.AboutYouActivity
import com.google.common.truth.Truth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyTestSuite {

    private val userId = Firebase.auth.currentUser?.uid
    private val database = Firebase.database.reference

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AboutYouActivity::class.java)
    @Test fun testEvent() {
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            if (userId != null) {
                activity.bindGender(userId, "male")
                Thread.sleep(2000)
                database.child("Users").child(userId).get().addOnSuccessListener {
                    Truth.assertThat(it.child("gender").value).isEqualTo("male")

                }.addOnFailureListener{

                }
            }
        }
    }
}