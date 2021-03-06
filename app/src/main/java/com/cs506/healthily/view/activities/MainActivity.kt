package com.cs506.healthily.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cs506.healthily.R
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.viewModel.DailyHeartPointsViewModel
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.JournalViewModel
import com.cs506.healthily.viewModel.goalViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.GoalsReadRequest
import com.google.android.gms.fitness.request.SessionReadRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

val TAG = "FIT"



class MainActivity : AppCompatActivity() {

    var stepDay = 1
    var heartDay = 1
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
        val profile : ImageView = findViewById<ImageView>(R.id.profile_button)

        profile.setOnClickListener {
            startActivity(
                Intent(
                    this, ProfileEditorActivity
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
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun readWeeklySteps() {
        // Read the data that's been collected throughout the past week.
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
      //  Log.i(TAG, "Range Start: $startTime")
      //  Log.i(TAG, "Range End: $endTime")

        val readRequest =
            DataReadRequest.Builder()
                .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()
        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readData(readRequest)
            .addOnSuccessListener { response ->
                // delete steps currently in database
                deleteDailySteps()
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    // iterates through data points
                    printAndPostStepsToFirebase(dataSet)
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG,"There was an error reading data from Google Fit", e)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun printAndPostStepsToFirebase(dataSet: DataSet) {
     //   Log.i(TAG, "Data returned for Data type: ${dataSet.dataType.name}")
        val day : DaySteps = DaySteps()

        if(dataSet.dataPoints.isEmpty()){
            day.steps = "0"
            day.day = stepDay.toString()
            bindData(day)
        }

        for (dp in dataSet.dataPoints) {
        //    Log.i(TAG,"Data point:")
           // Log.i(TAG,"\tType: ${dp.dataType.name}")
          //  Log.i(TAG,"\tStart: ${dp.getStartTimeString()}")
          //  Log.i(TAG,"\tEnd: ${dp.getEndTimeString()}")

            for (field in dp.dataType.fields) {
              //  Log.i(TAG,"\tField: ${field.name.toString()} Value: ${dp.getValue(field)}")
                day.steps = dp.getValue(field).toString()
                day.day = stepDay.toString()

                bindData(day)

            }
        }
        stepDay++
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun printAndPostHeartToFirebase(dataSet: DataSet) {
      //  Log.i(TAG, "Data returned for Data type: ${dataSet.dataType.name}")
        //val day : DaySteps = DaySteps()
        val day : DayHeart = DayHeart()
        if(dataSet.dataPoints.isEmpty()){
            day.heartPoints = "0"
            day.day = heartDay.toString()
            bindDataHeart(day)
        }

        for (dp in dataSet.dataPoints) {
          //  Log.i(TAG,"Data point:")
          //  Log.i(TAG,"\tType: ${dp.dataType.name}")
          //  Log.i(TAG,"\tStart: ${dp.getStartTimeString()}")
          //  Log.i(TAG,"\tEnd: ${dp.getEndTimeString()}")

            for (field in dp.dataType.fields) {
             //   Log.i(TAG,"\tField: ${field.name.toString()} Value: ${dp.getValue(field)}")
                day.heartPoints = dp.getValue(field).toString()
                day.day = heartDay.toString()

                bindDataHeart(day)

            }
        }

        heartDay++

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
     fun readWeeklyHP() {
        // Read the data that's been collected throughout the past week.
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
        val startTime = endTime.minusWeeks(1)
      //  Log.i(TAG, "Range Start: $startTime")
      //  Log.i(TAG, "Range End: $endTime")

        val readRequest =
            DataReadRequest.Builder()
                .aggregate(DataType.TYPE_HEART_POINTS)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()
        Fitness.getHistoryClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readData(readRequest)
            .addOnSuccessListener { response ->
                deleteDailyHeart()
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    // iterates through data points
                    printAndPostHeartToFirebase(dataSet)
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

     fun readHPGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(hpReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val heartPointGoal = metricObjective.value
                   // Log.i(TAG, "HP Goal: $heartPointGoal")
                }
            }
    }

    private val stepReadRequest: GoalsReadRequest by lazy {
        GoalsReadRequest.Builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .build()
    }


    @RequiresApi(Build.VERSION_CODES.O)
     fun bindDataHeart(day: DayHeart) {
        val viewModel: DailyHeartPointsViewModel =
            ViewModelProviders.of(this).get(DailyHeartPointsViewModel::class.java)
            viewModel.addDay(day)

    }


    @RequiresApi(Build.VERSION_CODES.O)
     fun deleteDailySteps() {
        val viewModel: DayStepsViewModel =
            ViewModelProviders.of(this).get(DayStepsViewModel::class.java)
        viewModel.deleteDailySteps()
    }

     fun readStepGoal() {
        Fitness.getGoalsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
            .readCurrentGoals(stepReadRequest)
            .addOnSuccessListener { goals ->
                goals.firstOrNull()?.apply {
                    val stepGoal = metricObjective.value
                  //  Log.i(TAG, "Step Goal: $stepGoal")
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun getActivities() {
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

                        deleteJournal()

                      //  val day = startTime.dayOfWeek
                        val day = startTime.dayOfMonth
                        val month = startTime.month
                        val date = startTime.dayOfWeek
                        val dayString = "$day, $month $date"




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
                                        Log.i(TAG, "activity type: $activityType")
                                      //  Log.i(TAG, "start time: $startTime")
                                       // Log.i(TAG, "end time: $endTime")
                                        Log.i(TAG, dayString)
                                        Log.i(TAG, "heart points: $heartPoints")
                                        val activity = JournalActivity()
                                        activity.date = dayString
                                        activity.heartPoints = heartPoints
                                        activity.activity = activityType
                                        bindJournalActivity(activity)
                                    }
                                }
                            }
                            .addOnFailureListener { e ->
                              //  Log.w(TAG,"There was an error reading data from Google Fit", e)
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
                                        Log.i(TAG, "activity type: $activityType")

                                        Log.i(TAG, dayString)

                                        Log.i(TAG, "step count: $stepCount")
                                        val activity = JournalActivity()
                                        activity.date = dayString
                                        //activity.heartPoints = heartPoints
                                        activity.stepCount = stepCount
                                        activity.activity = activityType
                                        bindJournalActivity(activity)
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

     fun deleteDailyHeart() {
        val viewModel: DailyHeartPointsViewModel =
            ViewModelProviders.of(this).get(DailyHeartPointsViewModel::class.java)
        viewModel.deleteDailyHeartPoints()

    }



    @RequiresApi(Build.VERSION_CODES.O)
     fun bindData(day: DaySteps) {
        val viewModel: DayStepsViewModel =
            ViewModelProviders.of(this).get(DayStepsViewModel::class.java)
            viewModel.addDay(day)
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun bindJournalActivity(activity: JournalActivity){
        val viewModel : JournalViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
            viewModel.addJournalActivity(activity)
    }

     fun deleteJournal(){
        val viewModel : JournalViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
            viewModel.deleteJournal()
    }
}



