package com.cs506.healthily.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.cs506.healthily.R
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.goalViewModel
import com.google.firebase.auth.FirebaseAuth

class HeartPointGoalActivity : AppCompatActivity() {
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
        
        // https://www.cdc.gov/physicalactivity/basics/age-chart.html
        
        
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