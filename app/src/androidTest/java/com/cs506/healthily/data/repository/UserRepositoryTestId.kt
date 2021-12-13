package com.cs506.healthily.data.repository

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test

class UserRepositoryTestId {
    val userId: String = "testUser"


    @Test
    fun testGetUserId(){
        val repository = UserSettingsRepository(userId)
        Thread.sleep(2000)
        assertEquals(repository.userId, "testUser")

    }

    @Test
    fun testGetSetUserId(){
        val repository = UserSettingsRepository(userId)
        repository.userId = "newUser"
        Thread.sleep(2000)
        assertEquals(repository.userId, "newUser")

    }
}