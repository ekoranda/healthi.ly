package com.cs506.healthily

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProviders
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.AboutYou
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.goalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule




