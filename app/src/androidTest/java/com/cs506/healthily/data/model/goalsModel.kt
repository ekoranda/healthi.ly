package com.cs506.healthily.data.model

import org.junit.Test

class goalsModelTest {
    @Test
    fun testGoal(){
        val goal:Goals = Goals("20", "6000")

        var b : Boolean = false
        if (goal.heartGoal == "20" && goal.stepGoal == "6000"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }

    @Test
    fun testGetGoal(){
        val goal:Goals = Goals()
        goal.heartGoal = "20"
        goal.stepGoal = "6000"

        var b : Boolean = false
        if (goal.heartGoal == "20" && goal.stepGoal == "6000"){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }
}