package com.cs506.healthily.view.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cs506.healthily.R
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.GoalsReadRequest
import com.google.android.gms.fitness.request.SessionReadRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

val TAG = "FIT"

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialize the bottom navigation view
        //create bottom navigation view object
        readWeeklySteps()
        readWeeklyHP()
        readHPGoal()
        readStepGoal()
        getActivities()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_POINTS, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun readWeeklySteps() {
        // Read the data that's been collected throughout the past week.
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
        Log.i(TAG, "Range Start: $startTime")
        Log.i(TAG, "Range End: $endTime")

        val readRequest =
            DataReadRequest.Builder()
                .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()
        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readData(readRequest)
            .addOnSuccessListener { response ->
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    // iterates through data points
                    printAndPostToFirebase(dataSet)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG,"There was an error reading data from Google Fit", e)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun printAndPostToFirebase(dataSet: DataSet) {
        Log.i(TAG, "Data returned for Data type: ${dataSet.dataType.name}")
        for (dp in dataSet.dataPoints) {
            Log.i(TAG,"Data point:")
            Log.i(TAG,"\tType: ${dp.dataType.name}")
            Log.i(TAG,"\tStart: ${dp.getStartTimeString()}")
            Log.i(TAG,"\tEnd: ${dp.getEndTimeString()}")

            for (field in dp.dataType.fields) {
                Log.i(TAG,"\tField: ${field.name.toString()} Value: ${dp.getValue(field)}")
                val day : DaySteps = DaySteps()
                day.steps = dp.getValue(field).toString()
                bindData(day)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun DataPoint.getStartTimeString() = Instant.ofEpochSecond(this.getStartTime(TimeUnit.SECONDS))
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime().toString()

    @RequiresApi(Build.VERSION_CODES.O)
    fun DataPoint.getEndTimeString() = Instant.ofEpochSecond(this.getEndTime(TimeUnit.SECONDS))
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime().toString()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun readWeeklyHP() {
        // Read the data that's been collected throughout the past week.
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
        Log.i(TAG, "Range Start: $startTime")
        Log.i(TAG, "Range End: $endTime")

        val readRequest =
            DataReadRequest.Builder()
                .aggregate(DataType.TYPE_HEART_POINTS)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()
        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readData(readRequest)
            .addOnSuccessListener { response ->
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    // iterates through data points
                    printAndPostToFirebase(dataSet)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG,"There was an error reading data from Google Fit", e)
            }
    }

    private val hpReadRequest: GoalsReadRequest by lazy {
        GoalsReadRequest.Builder()
            .addDataType(DataType.TYPE_HEART_POINTS)
            .build()
    }

    private fun readHPGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(hpReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val heartPointGoal = metricObjective.value
                    Log.i(TAG, "HP Goal: $heartPointGoal")
                }
            }
    }

    private val stepReadRequest: GoalsReadRequest by lazy {
        GoalsReadRequest.Builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .build()
    }

    private fun readStepGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(stepReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val stepGoal = metricObjective.value
                    Log.i(TAG, "Step Goal: $stepGoal")
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getActivities() {
        // Read the data that's been collected throughout the past week.
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
        val activitiesReadRequest = DataReadRequest.Builder()
            .aggregate(DataType.TYPE_ACTIVITY_SEGMENT)
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
            .build()

        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readData(activitiesReadRequest)
            .addOnSuccessListener { response ->
                for (dataSetMain in response.buckets.flatMap { it.dataSets }) {
                    for (dpMain in dataSetMain.dataPoints) {
                        val startTime = Instant.ofEpochSecond(dpMain.getStartTime(TimeUnit.SECONDS)).atZone(
                            ZoneId.systemDefault())
                        val endTime = Instant.ofEpochSecond(dpMain.getEndTime(TimeUnit.SECONDS)).atZone(
                            ZoneId.systemDefault())

                        val activityType = dpMain.getValue(Field.FIELD_ACTIVITY).asActivity()

                        Log.i(TAG, "activity type: $activityType")
                        Log.i(TAG, "start time: $startTime")
                        Log.i(TAG, "end time: $endTime")

                        // get heart points
                        val hpReadRequest = DataReadRequest.Builder()
                            .aggregate(DataType.TYPE_HEART_POINTS)
                            .bucketByTime(1, TimeUnit.DAYS)
                            .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                            .build()

                        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
                            .readData(hpReadRequest)
                            .addOnSuccessListener { response ->
                                for (dataSetHP in response.buckets.flatMap { it.dataSets }) {
                                    for (dpHp in dataSetHP.dataPoints) {
                                        val heartPoints = dpHp.getValue(Field.FIELD_INTENSITY).toString()
                                        Log.i(TAG, "heart points: $heartPoints")
                                    }
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG,"There was an error reading data from Google Fit", e)
                            }

                        //get step count
                        val stepReadRequest = DataReadRequest.Builder()
                            .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
                            .bucketByTime(1, TimeUnit.DAYS)
                            .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                            .build()


                        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
                            .readData(stepReadRequest)
                            .addOnSuccessListener { response ->
                                for (dataSetStep in response.buckets.flatMap { it.dataSets }) {
                                    for (dpStep in dataSetStep.dataPoints) {
                                        val stepCount = dpStep.getValue(Field.FIELD_STEPS).toString()
                                        Log.i(TAG, "step count: $stepCount")
                                    }
                                }
                            }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG,"Failed to read session", e)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindData(day: DaySteps) {
        val viewModel: DayStepsViewModel =
            ViewModelProviders.of(this).get(DayStepsViewModel::class.java)
            viewModel.addDay(day)
    }
}



