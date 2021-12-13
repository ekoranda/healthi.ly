package com.cs506.healthily.viewModel


import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.common.truth.Truth.assertThat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.After


import org.junit.Before
import org.junit.Test


class AboutYouTest {

    private lateinit var viewModel: AboutYou


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = AboutYou(getApplicationContext())
    }

    private fun tearDown(){
        database.child("Users/testingAccount").removeValue()
    }










    /**
     * Test the function setGenderFromRepo(userId: String, gender: String) of AboutYou viewModel.
     * when: userId = "setGenderFromRepoOfAboutYou4", gender = "male", this function should work.
     * function setGenderFromRepo(userId: String, gender: String) should set userId "setGenderFromRepoOfAboutYou4"
     *     gender to "male" from database
     */
    @Test
    fun setGenderFromRepoOfAboutYou4(){
        val userId = "testingAccount"
        val gender = "male"
        viewModel.setGenderFromRepo(userId, gender)

        database.child("Users").child(userId).get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child("gender").value).isEqualTo("male")
            tearDown()
        }.addOnFailureListener{

        }

    }




















}

class setAge{

    private lateinit var viewModel: AboutYou


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = AboutYou(getApplicationContext())
    }

    private fun tearDown(){
        database.child("Users/testingAccount").removeValue()
    }




    @Test
    fun setAgeFromRepoOfAboutYou4(){
        val userId = "testingAccount"
        val age = "20"
        viewModel.setAgeFromRepo(userId, age)

        database.child("Users").child(userId).get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child("age").value).isEqualTo("20")
            tearDown()
        }.addOnFailureListener{

        }

    }


}



class setWeight {

    private lateinit var viewModel: AboutYou


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = AboutYou(getApplicationContext())
    }

    private fun tearDown(){
        database.child("Users/testingAccount").removeValue()
    }
    /**
     * Test the function setWeightFromRepo(userId: String, weight: String) of AboutYou viewModel.
     * when: userId = "setWeightFromRepoOfAboutYou4", weight = "60", this function should work.
     * function setWeightFromRepo(userId: String, weight: String) should set userId "setWeightFromRepoOfAboutYou4"
     *     weight to "60" from database
     */
    @Test
    fun setWeightFromRepoOfAboutYou4(){
        val userId = "testingAccount"
        val weight = "60"
        viewModel.setWeightFromRepo(userId, weight)

        database.child("Users").child(userId).get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child("weight").value).isEqualTo("60")
            tearDown()
        }.addOnFailureListener{

        }

    }

}

class setHeight{

    private lateinit var viewModel: AboutYou


    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = AboutYou(getApplicationContext())
    }

    private fun tearDown(){
        database.child("Users/testingAccount").removeValue()
    }
    /**
     * Test the function setHeightFromRepo(userId: String, height: String) of AboutYou viewModel.
     * when: userId = "setHeightFromRepoOfAboutYou4", height = "180", this function should work.
     * function setHeightFromRepo(userId: String, height: String) should set userId "setHeightFromRepoOfAboutYou4"
     *     height to "180" from database
     */
    @Test
    fun setHeightFromRepoOfAboutYou4(){
        val userId = "testingAccount"
        val height = "180"
        viewModel.setHeightFromRepo(userId, height)

        database.child("Users").child(userId).get().addOnSuccessListener {
            Thread.sleep(2000)
            assertThat(it.child("height").value).isEqualTo("180")
            tearDown()

        }.addOnFailureListener{

        }


    }
}

class setAvailabilityEnd {
    private lateinit var viewModel: AboutYou
    private val database = Firebase.database.reference
    val userId = "testingAccount"

    @Test
    fun setAvailEnd(){
        viewModel = AboutYou(getApplicationContext())
        viewModel.setAvailabilityEndFromRepo(userId, "6pm")
        database.child("Users").child(userId).get().addOnSuccessListener {
            val endTime = it.child("availabilityEnd").value
            Thread.sleep(2000)
            assertEquals("6pm", endTime)
            database.child("Users/testingAccount").removeValue()

        }
    }


}


/*


 */



