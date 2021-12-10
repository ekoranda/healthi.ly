package com.cs506.healthily.viewModel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.cs506.healthily.data.model.DaySteps
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.rules.TestRule

class DailyStepTest {
    private lateinit var viewModel: DayStepsViewModel
    private val userId = Firebase.auth.currentUser?.uid

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = DayStepsViewModel(getApplicationContext())
    }



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /*














     */






}