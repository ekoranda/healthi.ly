package com.cs506.healthily.otherTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cs506.healthily.data.repository.JournalActivityRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class testJournal {
}

/*
class JournalActivityRepositoryTest {
    private val database = Firebase.database.reference
    private val user = Firebase.auth.currentUser?.uid

    val repository : JournalActivityRepository = JournalActivityRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()



    @Test
    fun testGetAllActivities(){
        database.child("Users/$user/dailyActivity/fakeActivity/activity").setValue("fakeActivity")
        database.child("Users/$user/dailyActivity/fakeActivity/stepCount").setValue("1234")

        repository.getAllActivities()?.observeForever{ mList ->
            for(act in mList){
                Thread.sleep(200000)
                if(act.activity == "fakeActivity"){
                    assertEquals("1234", act.stepCount)
                    database.child("Users/$user/dailyActivity/fakeActivity").removeValue()
                }
            }

        }

    }














}



 */