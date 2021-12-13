package com.cs506.healthily.data.model

import org.junit.Test

class JournalActivityTest {
    @Test
    fun testJournal(){
        val journal : JournalActivity = JournalActivity()
        journal.heartPoints = "20"
        journal.activity = "running"
        journal.stepCount = "3000"
        journal.date = "testDay"


        var b : Boolean = false
        if (journal.heartPoints == "20" && journal.activity == "running" && journal.stepCount == "3000" && journal.date == "testDay"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)
    }

    @Test
    fun testSetJournal(){
        val journal : JournalActivity = JournalActivity("testDay", "running", "3000", "20")
        var b : Boolean = false
        if (journal.heartPoints == "20" && journal.activity == "running" && journal.stepCount == "3000" && journal.date == "testDay"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)
    }
}