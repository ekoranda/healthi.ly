package com.cs506.healthily.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.view.logger.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class goalViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = goalViewModel(api)


    @Test
    fun testGetUserSettings() {


        viewModel.getUserSettings()?.observeForever{ mList ->
            val age = mList.age
            database.child("Users/$user/age").get().addOnSuccessListener {
                Assert.assertEquals(age, it.value)
            }
        }





    }
}

class testGetUserId{
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = goalViewModel(api)
    @Test
    fun testGetUserId(){
        val userId = viewModel.userId
        assertEquals(user, userId)
    }

}

class testGetAge{
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = goalViewModel(api)
    @Test
    fun testGetAge(){
        val age = user?.let { viewModel.getAge(it) }
        assertEquals("0", age)
    }

}

class testSetHeartGoal{
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = goalViewModel(api)
    @Test
    fun testSetHeartGoal(){

        viewModel.setHeartGoal("90")
        database.child("Users/$user/heartGoal").get().addOnSuccessListener {
            Assert.assertEquals("90", it.value)
        }



    }

}

class testSetStepGoal{
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val api: Application = ApplicationProvider.getApplicationContext()
    val viewModel = goalViewModel(api)
    @Test
    fun testSetHeartGoal(){

        viewModel.setStepGoal("9999")
        database.child("Users/$user/stepGoal").get().addOnSuccessListener {
            Assert.assertEquals("9999", it.value)
        }



    }

}

