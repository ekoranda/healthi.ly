package com.cs506.healthily

import androidx.lifecycle.ViewModelProviders
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.viewModel.goalViewModel

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cs506.healthily", appContext.packageName)
    }
    @Test
    fun testSetHeartGoal() {
        val goalRepository: GoalsRepository = GoalsRepository("zB30YssDeYds297P4GLiIDYCiUh2")
        assertNotEquals(null, goalRepository)
        goalRepository.setHeartGoal("Testing")
        var temp: String? = goalRepository.getGoals().toString()
        assertEquals("Testing", temp)
    }
}