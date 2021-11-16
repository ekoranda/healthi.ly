package com.cs506.healthily.data.model

class DaySteps {
    var day: String? = null
    var steps: String? = null


    constructor(){}

    constructor(
        day:String?,
        steps: String?,
    ){
        this.day = day
        this.steps = steps

    }
}