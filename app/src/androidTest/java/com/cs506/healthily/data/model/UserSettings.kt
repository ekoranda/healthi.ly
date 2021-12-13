package com.cs506.healthily.data.model

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.cs506.healthily.viewModel.goalViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UserSettingsTest {
    @Test
    fun testUserSettingsWithParam(){
        val user: UserSettings = UserSettings( "female", "21", "200", "5'11", "9am", "11am")
        var b : Boolean = false
        if (user.gender == "female" && user.age == "21" && user.weight == "200" && user.height == "5'11" && user.availabilityStart == "9am" && user.availabilityEnd=="11am"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }

    @Test
    fun getUserSettings(){
        val user: UserSettings = UserSettings()
        user.gender="female"
        user.age = "21"
        user.weight = "200"
        user.height = "5'11"
        user.availabilityStart = "9am"
        user.availabilityEnd = "11am"
        var b : Boolean = false
        if (user.gender == "female" && user.age == "21" && user.weight == "200" && user.height == "5'11" && user.availabilityStart == "9am" && user.availabilityEnd=="11am"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)
    }
}

