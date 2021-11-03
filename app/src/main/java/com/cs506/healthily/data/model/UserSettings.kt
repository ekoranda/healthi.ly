package com.cs506.healthily.data.model

class UserSettings {
    var gender: String? = null
    var age: String? = null
    var weight: String? = null
    var height: String? = null

    constructor(){}

    constructor(
        gender:String?,
        age: String?,
        weight: String?,
        height: String?
    ){
        this.gender = gender
        this.age = age
        this.weight = weight
        this.height = height
    }
}