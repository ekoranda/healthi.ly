package com.cs506.healthily.data.model

class DayHeart {
    var day: String? = null
    var heartPoints: String? = null
    var heartGoal: String? = null


    constructor(){}

    constructor(
        day:String?,
        heartPoints: String?,
        heartGoal: String,
    ){
        this.day = day
        this.heartPoints = heartPoints
        this.heartGoal = heartGoal

    }
}