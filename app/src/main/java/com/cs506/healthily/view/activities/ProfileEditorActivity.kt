package com.cs506.healthily.view.activities

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class ProfileEditorActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_profile_editor)
//    }
//}



//package com.cs506.healthily.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.cs506.healthily.R
import com.cs506.healthily.viewModel.AboutYou
import com.cs506.healthily.viewModel.goalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileEditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_editor)
        setUpSpinners()

        val next : Button = findViewById<Button>(R.id.btn_next)

        next.setOnClickListener {
            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }
    }


    private fun setUpSpinners() {
        val genders = resources.getStringArray(R.array.Genders)
        val genderSpinner = findViewById<Spinner>(R.id.sp_gender)
        if (genderSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, genders
            )
            genderSpinner.adapter = adapter

            // Added code that should set the spinner to whatever the user's current settings are
            var gender: String = ""
            val goalViewModel: goalViewModel =
                ViewModelProviders.of(this).get(goalViewModel::class.java)
            goalViewModel.getUserSettings()?.observe(this) { goals ->
                gender = goals.gender.toString()
            }
            var genderIndex = 0
            for (elem in genders.indices) {
                if (genders[elem].toString().contentEquals(gender)) {
                    genderIndex = elem
                }
            }
            genderSpinner.setSelection(genderIndex)

            genderSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing Selected")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val gender = genders[position]
                    val user = Firebase.auth.currentUser?.uid
                    if (user != null) {
                        bindGender(user, gender)
                    }
                }

            }

        }

        val ages = resources.getStringArray(R.array.Ages)
        val ageSpinner = findViewById<Spinner>(R.id.sp_age)
        if (ageSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, ages
            )
            ageSpinner.adapter = adapter
            ageSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing Selected")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val age = ages[position]
                    val user = Firebase.auth.currentUser?.uid
                    if (user != null) {
                        bindAge(user, age)
                    }
                }

            }
        }


        val weights = resources.getStringArray(R.array.Weights)
        val weightSpinner = findViewById<Spinner>(R.id.sp_weight)
        if (weightSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, weights
            )
            weightSpinner.adapter = adapter
            weightSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing Selected")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val weight = weights[position]
                    val user = Firebase.auth.currentUser?.uid
                    if (user != null) {
                        bindWeight(user, weight)
                    }
                }

            }
        }

        val heights  = arrayOf("Height:", "5'1\"",
            "5'2\"",
            "5'3\"",
            "5'4\"",
            "5'5\"",
            "5'6\"",
            "5'7\"",
            "5'8\"",
            "5'9\"",
            "5'11\"",
            "6'0\"",
            "6'1\"",
            "6'2\"",
            "6'3\"",
            "6'4\"",
            "6'5\""
        )



        val heightSpinner = findViewById<Spinner>(R.id.sp_height)
        if (heightSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, heights
            )
            heightSpinner.adapter = adapter
            heightSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing Selected")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    val height = heights[position]
                    val user = Firebase.auth.currentUser?.uid
                    if (user != null) {
                        bindHeight(user, height)
                    }
                }

            }
        }



    }

    private fun bindGender(userId: String, gender: String) {

        val aboutYouViewModel: AboutYou = ViewModelProviders.of(this).get(AboutYou::class.java)
        aboutYouViewModel.setGenderFromRepo(userId, gender)

    }

    private fun bindAge(userId: String, age: String) {

        val aboutYouViewModel: AboutYou = ViewModelProviders.of(this).get(AboutYou::class.java)
        aboutYouViewModel.setAgeFromRepo(userId, age)

    }

    private fun bindHeight(userId: String, height: String) {

        val aboutYouViewModel: AboutYou = ViewModelProviders.of(this).get(AboutYou::class.java)
        aboutYouViewModel.setHeightFromRepo(userId, height)

    }

    private fun bindWeight(userId: String, weight: String) {

        val aboutYouViewModel: AboutYou = ViewModelProviders.of(this).get(AboutYou::class.java)
        aboutYouViewModel.setWeightFromRepo(userId, weight)

    }








}