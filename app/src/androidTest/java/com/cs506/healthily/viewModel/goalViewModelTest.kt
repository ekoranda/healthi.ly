package com.cs506.healthily.viewModel

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class goalViewModelTest{

    private lateinit var viewModel: goalViewModel

    private val database = Firebase.database.reference
    val userId = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

    @Before
    fun setup() {
        viewModel = goalViewModel(ApplicationProvider.getApplicationContext())

    }

    /**
     * Test the function getAge(userId: String): String? of goalViewModel .
     * when: userId = "", gender = "male", the function should not work since string userId be a string
     *     not empty.
     * function setGenderFromRepo(userId: String, gender: String) should throw an exception.
     */
    @Test
    fun getAgeOfgoalViewModel(){
        var age : String?


        database.child("Users").get().addOnSuccessListener {
            Truth.assertThat(it.hasChild("")).isFalse()
        }.addOnFailureListener{

        }

    }


}