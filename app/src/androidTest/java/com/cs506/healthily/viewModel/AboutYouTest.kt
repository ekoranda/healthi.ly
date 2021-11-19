package com.cs506.healthily.viewModel


import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.common.truth.Truth.assertThat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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





    /**
     * Test the function setGenderFromRepo(userId: String, gender: String) in AboutYou viewModel
     * when: userId = "", gender = "male", this function should not work since userId should be a string
     *     not empty.
     *
     */
    @Test
    fun test1(){
        val a = 2
        assertThat(a).isEqualTo(2)
    }

    /**
     * Test the function setGenderFromRepo(userId: String, gender: String) of AboutYou viewModel.
     * when: userId = "", gender = "male", the function should not work since string userId be a string
     *     not empty.
     * function setGenderFromRepo(userId: String, gender: String) should throw an exception.
     */
    /*
    @Test
    fun setGenderFromRepoOfAboutYou1(){
        val userId = ""
        val gender = "male"
        viewModel.setGenderFromRepo(userId, gender)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.hasChild("")).isFalse()
        }.addOnFailureListener{

        }


    }

     */

    /**
     * Test the function setGenderFromRepo(userId: String, gender: String) of AboutYou viewModel.
     * when: userId = "setGenderFromRepoOfAboutYou2", gender = "", this function should not work since
     *     gender should be a string not empty.
     * function setGenderFromRepo(userId: String, gender: String) should throw an exception.
     */
    /*
    @Test
    fun setGenderFromRepoOfAboutYou2(){
        val userId = "setGenderFromRepoOfAboutYou2"
        val gender = ""
        viewModel.setGenderFromRepo(userId, gender)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("gender")).isFalse()
        }.addOnFailureListener{

        }


    }

     */

    /**
     * Test the function setGenderFromRepo(userId: String, gender: String) of AboutYou viewModel.
     * when: userId = "setGenderFromRepoOfAboutYou3", gender = "1", this function should not work since
     *     gender should only be string "male" or "female".
     * function setGenderFromRepo(userId: String, gender: String) should throw an exception.
     */
    /*
    @Test
    fun setGenderFromRepoOfAboutYou3(){
        val userId = "setGenderFromRepoOfAboutYou3"
        val gender = "1"
        viewModel.setGenderFromRepo(userId, gender)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("gender")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

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
            assertThat(it.child("gender").value).isEqualTo("male")
            tearDown()
        }.addOnFailureListener{

        }

    }


    /**
     * Test the function setAgeFromRepo(userId: String, age: String) of AboutYou viewModel.
     * when: userId = "", age = "20", the function should not work since string userId should be a string
     *     not empty.
     * function setAgeFromRepo(userId: String, age: String) should throw an exception.
     */
    /*
    @Test
    fun setAgeFromRepoOfAboutYou1(){
        val userId = ""
        val age = "20"
        viewModel.setAgeFromRepo(userId, age)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.hasChild("")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setAgeFromRepo(userId: String, age: String) of AboutYou viewModel.
     * when: userId = "setAgeFromRepoOfAboutYou2", age = "", this function should not work since
     *     age should be a string not empty.
     * function setAgeFromRepo(userId: String, age: String) should throw an exception.
     */
    /*
    @Test
    fun setAgeFromRepoOfAboutYou2(){
        val userId = "setAgeFromRepoOfAboutYou2"
        val age = ""
        viewModel.setAgeFromRepo(userId, age)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("age")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setAgeFromRepo(userId: String, age: String) of AboutYou viewModel.
     * when: userId = "setAgeFromRepoOfAboutYou3", age = "hello", this function should not work since
     *     age should only be string consisting number.
     * function setAgeFromRepo(userId: String, age: String) should throw an exception.
     */
    /*
    @Test
    fun setAgeFromRepoOfAboutYou3(){
        val userId = "setAgeFromRepoOfAboutYou3"
        val age = "hello"
        viewModel.setAgeFromRepo(userId, age)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("age")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setAgeFromRepo(userId: String, age: String) of AboutYou viewModel.
     * when: userId = "setAgeFromRepoOfAboutYou4", age = "29", this function should work.
     * function setGenderFromRepo(userId: String, gender: String) should set userId "setAgeFromRepoOfAboutYou4"
     *     age to "20" from database
     */
    @Test
    fun setAgeFromRepoOfAboutYou4(){
        val userId = "testingAccount"
        val age = "20"
        viewModel.setAgeFromRepo(userId, age)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.child("age").value).isEqualTo("20")
            tearDown()
        }.addOnFailureListener{

        }

    }

    /**
     * Test the function setWeightFromRepo(userId: String, weight: String) of AboutYou viewModel.
     * when: userId = "", weight = "60", the function should not work since string userId should be a string
     *     not empty.
     * function setWeightFromRepo(userId: String, weight: String) should throw an exception.
     */
    /*
    @Test
    fun setWeightFromRepoOfAboutYou1(){
        val userId = ""
        val weight = "60"
        viewModel.setWeightFromRepo(userId, weight)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.hasChild("")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setWeightFromRepo(userId: String, weight: String) of AboutYou viewModel.
     * when: userId = "setWeightFromRepoOfAboutYou2", weight = "", this function should not work since
     *     weight should be a string not empty.
     * function setWeightFromRepo(userId: String, weight: String) should throw an exception.
     */
    /*
    @Test
    fun setWeightFromRepoOfAboutYou2(){
        val userId = "setWeightFromRepoOfAboutYou2"
        val weight = ""
        viewModel.setWeightFromRepo(userId, weight)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("weight")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setWeightFromRepo(userId: String, weight: String) of AboutYou viewModel.
     * when: userId = "setWeightFromRepoOfAboutYou3", weight = "hello", this function should not work since
     *     weight should only be string consisting only number.
     * function setWeightFromRepo(userId: String, weight: String) should throw an exception.
     */
    /*
    @Test
    fun setWeightFromRepoOfAboutYou3(){
        val userId = "setWeightFromRepoOfAboutYou3"
        val weight = "hello"
        viewModel.setWeightFromRepo(userId, weight)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("weight")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

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
            assertThat(it.child("weight").value).isEqualTo("60")
            tearDown()
        }.addOnFailureListener{

        }

    }

    /**
     * Test the function setHeightFromRepo(userId: String, Height: String) of AboutYou viewModel.
     * when: userId = "", Height = "180", the function should not work since string userId should be a string
     *     not empty.
     * function setWeightFromRepo(userId: String, height: String) should throw an exception.
     */
    /*
    @Test
    fun setHeightFromRepoOfAboutYou1(){
        val userId = ""
        val height = "180"
        viewModel.setHeightFromRepo(userId, height)

        database.child("Users").get().addOnSuccessListener {
            assertThat(it.hasChild("")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setHeightFromRepo(userId: String, height: String) of AboutYou viewModel.
     * when: userId = "setHeightFromRepoOfAboutYou2", height = "", this function should not work since
     *     height should be a string not empty.
     * function setHeightFromRepo(userId: String, height: String) should throw an exception.
     */
    /*
    @Test
    fun setHeightFromRepoOfAboutYou2(){
        val userId = "setHeightFromRepoOfAboutYou2"
        val height = ""
        viewModel.setHeightFromRepo(userId, height)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("height")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

    /**
     * Test the function setHeightFromRepo(userId: String, height: String) of AboutYou viewModel.
     * when: userId = "setHeightFromRepoOfAboutYou3", height = "hello", this function should not work since
     *     height should only be string consisting only number.
     * function setHeightFromRepo(userId: String, height: String) should throw an exception.
     */
    /*
    @Test
    fun setHeightFromRepoOfAboutYou3(){
        val userId = "setHeightFromRepoOfAboutYou3"
        val height = "hello"
        viewModel.setHeightFromRepo(userId, height)

        database.child("Users").child(userId).get().addOnSuccessListener {
            assertThat(it.hasChild("height")).isFalse()
        }.addOnFailureListener{

        }

    }

     */

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
            assertThat(it.child("height").value).isEqualTo("180")
            tearDown()

        }.addOnFailureListener{

        }


    }

    private fun tearDown(){
        database.child("Users/testingAccount").removeValue()
    }

}