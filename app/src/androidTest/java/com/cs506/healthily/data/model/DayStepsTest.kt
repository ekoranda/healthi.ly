package com.cs506.healthily.data.model

import org.junit.Test

class DayStepsTest {
    @Test
    fun testDaySteps(){
        val day : DaySteps = DaySteps("testDay", "3000", "40000")
        var b  = false
        day.stepGoal = "50000"
        if(day.stepGoal == "50000"){
            b = true
        }

        Thread.sleep(2000)
        assert(b)

    }
}