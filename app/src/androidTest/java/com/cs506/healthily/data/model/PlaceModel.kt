package com.cs506.healthily.data.model

import org.junit.Test

class PlaceModelTest {
    @Test
    fun testPlaceModel(){
        val place: PlaceModel = PlaceModel(5, 6, "s1", "s2")
        var b : Boolean = false
        if (place.id == 5 && place.drawableId == 6 &&  place.name == "s1" && place.placeType == "s2" ){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }

    @Test
    fun testSetPlaceModel(){
        val place: PlaceModel = PlaceModel(0, 0, "null", "null")
        place.id = 1
        place.drawableId = 2
        place.name = "name"
        place.placeType = "placeType"
        var b : Boolean = false
        if (place.id == 1 && place.drawableId == 2 &&  place.name == "name" && place.placeType == "placeType" ){
            b = true
        }
        Thread.sleep(2000)
        assert(b)

    }
}

