package com.cs506.healthily.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cs506.healthily.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

class SignInGoogleFitActivity : AppCompatActivity() {

    var fitnessOptions : FitnessOptions? = null
    val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_google_fit)

        checkGoogleFitPermissions()
    }

    fun checkGoogleFitPermissions(){
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_HEART_POINTS, FitnessOptions.ACCESS_READ)
            .build()

        val account = GoogleSignIn.getAccountForExtension(this, fitnessOptions)

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {

            GoogleSignIn.requestPermissions(
                this, // your activity
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, // e.g. 1
                account,
                fitnessOptions)

        } else {

            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE -> accessGoogleFit()
                else -> {
                    // Result wasn't from Google Fit
                }
            }
            else -> {
                // Permission not granted
            }
        }
    }

    private fun accessGoogleFit() {
        startActivity(
            Intent(
                this, AboutYouActivity
                ::class.java
            )
        )
        finish()
    }


}