package com.cs506.healthily.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GoalsRepositoryTest {
    val userId: String = "testUser"


    @Test
    fun testGetUserId(){
        val repository : UserSettingsRepository = UserSettingsRepository(userId)
        Thread.sleep(2000)
        assertEquals(repository.userId, "testUser")

    }

    @Test
    fun testSetUserId(){
        val repository = UserSettingsRepository(userId)
        repository.userId = "newUser"
        assertEquals(repository.userId, "newUser")
    }

}

