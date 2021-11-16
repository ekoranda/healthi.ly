package com.cs506.healthily.data.model

class DaySteps {
    var day: String? = null
    var steps: String? = null
    var stepGoal: String? = null


    constructor(){}

    constructor(
        day:String?,
        steps: String?,
        stepGoal: String,
    ){
        this.day = day
        this.steps = steps
        this.stepGoal = stepGoal

    }
}