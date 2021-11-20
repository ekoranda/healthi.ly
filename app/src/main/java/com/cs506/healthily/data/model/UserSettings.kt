package com.cs506.healthily.data.model

class UserSettings {
    var gender: String? = null
    var age: String? = null
    var weight: String? = null
    var height: String? = null
    var availabilityStart: String? = null
    var availabilityEnd: String? = null

    constructor(){}

    constructor(
        gender:String?,
        age: String?,
        weight: String?,
        height: String?,
        availabilityStart: String?,
        availabilityEnd: String?
    ){
        this.gender = gender
        this.age = age
        this.weight = weight
        this.height = height
        this.availabilityStart = availabilityStart
        this.availabilityEnd = availabilityEnd
    }
}