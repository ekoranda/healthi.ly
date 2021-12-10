package com.cs506.healthily.data.model

import org.junit.Test

class DayHeartTest {
    @Test
    fun testDayHeart(){
        val day : DayHeart = DayHeart()
        day.day = "testDay"
        day.heartPoints = "20"
        day.heartGoal = "40"

        var b : Boolean = false
        if (day.day == "testDay" && day.heartPoints == "20" && day.heartGoal == "40"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }

    @Test
    fun setDayHeartTest(){
        val day = DayHeart("testDay", "20" , "40")
        var b : Boolean = false
        if (day.day == "testDay" && day.heartPoints == "20" && day.heartGoal == "40"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)
    }

}