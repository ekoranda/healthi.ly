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
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.model.Goals
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.goalViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.GoalsReadRequest
import com.google.firebase.auth.FirebaseAuth

class StepCountGoalActivity : AppCompatActivity() {
    var TAG = "FIT"
    var age: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_count_goal)
        setButtons()
        getGoal()

        val nextBtn : Button = findViewById(R.id.btn_next)
        nextBtn.setOnClickListener {
            startActivity(
                Intent(
                    this, HeartPointGoalActivity
                    ::class.java
                )
            )
            finish()
        }

        val importBtn : Button = findViewById(R.id.btn_import_from_google)
        importBtn.setOnClickListener {
            readStepGoal()
            startActivity(
                Intent(
                    this, HeartPointGoalActivity
                    ::class.java
                )
            )
            finish()
        }




    }

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
            .build()
    }


    private val stepReadRequest: GoalsReadRequest by lazy {
        GoalsReadRequest.Builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun readStepGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(stepReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val stepGoal = metricObjective.value

                    Log.i(TAG, "Step Goal: $stepGoal")
                    bindStepGoal(stepGoal.toInt().toString())
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindStepGoal(goal: String) {
        val viewModel: goalViewModel =
            ViewModelProviders.of(this).get(goalViewModel::class.java)
            viewModel.setStepGoal(goal)

    }



    private fun setButtons() {
        val stepGoalViewModel: goalViewModel =
            ViewModelProviders.of(this).get(goalViewModel::class.java)
            stepGoalViewModel.getUserSettings()?.observe(this) { goals ->
            age = goals.age.toString()
                var stepGoal: String = " "
                var ageInt = age.toInt()
                if (ageInt != null) {
                    if(ageInt > 65){
                        stepGoal = "6000"
                    }else if(ageInt >= 20){
                        stepGoal= "6000"
                    }
                    else{
                        stepGoal =  "9000"
                    }
                }

                val stepBtn1 : Button = findViewById(R.id.btn_heart_goal_1)
                stepBtn1.text = stepGoal

                var stepGoal2: String = " "
                if (ageInt != null) {
                    if(ageInt > 65){
                        stepGoal2 = "7000"
                    }else if(ageInt >= 20){
                        stepGoal2= "10000"
                    }
                    else{
                        stepGoal2 =  "11000"
                    }
                }
                val stepBtn2 : Button = findViewById(R.id.btn_heart_goal_2)
                stepBtn2.text = stepGoal2

                var stepGoal3: String = " "
                if (ageInt != null) {
                    if(ageInt > 65){
                        stepGoal3 = "8000"
                    }else if(ageInt >= 20){
                        stepGoal3= "11000"
                    }
                    else{
                        stepGoal3 =  "12000"
                    }
                }
                val stepBtn3 : Button = findViewById(R.id.btn_heart_goal_3)
                stepBtn3.text = stepGoal3



            }
    }

    private fun getGoal(){
        val btn1 : Button = findViewById(R.id.btn_heart_goal_1)
        btn1.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepGoal(btn1.text.toString())
        }

        val btn2 : Button = findViewById(R.id.btn_heart_goal_2)
        btn2.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepGoal(btn2.text.toString())
        }

        val btn3 : Button = findViewById(R.id.btn_heart_goal_3)
        btn3.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepGoal(btn3.text.toString())
        }
    }


}