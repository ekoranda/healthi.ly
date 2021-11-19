package com.cs506.healthily.viewModel

import androidx.test.core.app.ApplicationProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before

class goalViewModelTest{

    private lateinit var viewModel: goalViewModel

    private val database = Firebase.database.reference

    @Before
    fun setup() {
        viewModel = goalViewModel(ApplicationProvider.getApplicationContext())
    }
}