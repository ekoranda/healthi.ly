package com.cs506.healthily.data.model

class JournalActivity {
    var date: String? = null
    var activity: String? = null
    var stepCount: String? = null
    var heartPoints: String? = null

    constructor(){}

    constructor(
        date:String?,
        activity: String?,
        stepCount: String?,
        heartPoints: String?
    ){
        this.date = date
        this.activity = activity
        this.stepCount = stepCount
        this.heartPoints = heartPoints
    }
}