package com.cs506.healthily.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.cs506.healthily.R
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.stepCountGoal
import com.google.firebase.auth.FirebaseAuth

class StepCountGoalActivity : AppCompatActivity() {

    var age: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_count_goal)
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




    }



    private fun setButtons() {
        val stepGoalViewModel: stepCountGoal =
            ViewModelProviders.of(this).get(stepCountGoal::class.java)
            stepGoalViewModel.getGoalOne()?.observe(this) { goals ->
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

                val stepBtn1 : Button = findViewById(R.id.btn_step_goal_1)
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
                val stepBtn2 : Button = findViewById(R.id.btn_step_goal_2)
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
                val stepBtn3 : Button = findViewById(R.id.btn_step_goal_3)
                stepBtn3.text = stepGoal3



            }
    }

    private fun getGoal(){
        val btn1 : Button = findViewById(R.id.btn_step_goal_1)
        btn1.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepsGoal(btn1.text.toString())
        }

        val btn2 : Button = findViewById(R.id.btn_step_goal_2)
        btn2.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepsGoal(btn2.text.toString())
        }

        val btn3 : Button = findViewById(R.id.btn_step_goal_3)
        btn3.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.let {
                GoalsRepository(
                    it.uid
                )
            }?.setStepsGoal(btn3.text.toString())
        }
    }


}