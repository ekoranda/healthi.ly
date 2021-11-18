package com.cs506.healthily.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.cs506.healthily.R
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.goalViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.GoalsReadRequest
import com.google.firebase.auth.FirebaseAuth

class HeartPointGoalActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_point_goal)
        setButtons()
        getGoal()

        val nextBtn : Button = findViewById(R.id.btn_next)
        nextBtn.setOnClickListener {
            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }

        val importBtn : Button = findViewById(R.id.btn_import_from_google)
        importBtn.setOnClickListener {
            readHeartGoal()
            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }
        
        // https://www.cdc.gov/physicalactivity/basics/age-chart.html
        
        
    }

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
            .build()
    }


    private val hpReadRequest: GoalsReadRequest by lazy {
        GoalsReadRequest.Builder()
            .addDataType(DataType.TYPE_HEART_POINTS)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun readHeartGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(hpReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val heartGoal = metricObjective.value

                    Log.i(TAG, "Heart Goal: $heartGoal")
                    bindHeartGoal(heartGoal.toInt().toString())
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindHeartGoal(goal: String) {
        val viewModel: goalViewModel =
            ViewModelProviders.of(this).get(goalViewModel::class.java)
            viewModel.setHeartGoal(goal)

    }

    var age: String = ""
    private fun setButtons() {
        val goalViewModel: goalViewModel =
            ViewModelProviders.of(this).get(goalViewModel::class.java)
        goalViewModel.getUserSettings()?.observe(this) { goals ->
            age = goals.age.toString()
            var heartGoal: String = " "
            var ageInt = age.toInt()
            if (ageInt != null) {
                if(ageInt >= 65){
                    heartGoal = "20"
                }else if(ageInt >= 18){
                    heartGoal= "30"
                }
                else{
                    heartGoal =  "60"
                }
            }

            val heartBtn1 : Button = findViewById(R.id.btn_heart_goal_1)
            heartBtn1.text = heartGoal

            var heartGoal2: String = " "
            if (ageInt != null) {
                if(ageInt >= 65){
                    heartGoal2 = "25"
                }else if(ageInt >= 18){
                    heartGoal2= "40"
                }
                else{
                    heartGoal2 =  "70"
                }
            }
            val heartBtn2 : Button = findViewById(R.id.btn_heart_goal_2)
            heartBtn2.text = heartGoal2

            var heartGoal3: String = " "
            if (ageInt != null) {
                if(ageInt >= 65){
                    heartGoal3 = "30"
                }else if(ageInt >= 18){
                    heartGoal3= "50"
                }
                else{
                    heartGoal3 =  "80"
                }
            }
            val heartBtn3 : Button = findViewById(R.id.btn_heart_goal_3)
            heartBtn3.text = heartGoal3



        }
    }

    private fun getGoal(){
        val btn1 : Button = findViewById(R.id.btn_heart_goal_1)
        btn1.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setHeartGoal(btn1.text.toString())
        }

        val btn2 : Button = findViewById(R.id.btn_heart_goal_2)
        btn2.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setHeartGoal(btn2.text.toString())
        }

        val btn3 : Button = findViewById(R.id.btn_heart_goal_3)
        btn3.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setHeartGoal(btn3.text.toString())
        }
    }
}