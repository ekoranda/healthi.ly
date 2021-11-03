package com.cs506.healthily.data.model

class Goals {
    var heartGoal: String? = null
    var stepGoal: String? = null


    constructor(){}

    constructor(
        heartGoal:String?,
        stepGoal: String?,
    ){
        this.heartGoal = heartGoal
        this.stepGoal = heartGoal

    }
}