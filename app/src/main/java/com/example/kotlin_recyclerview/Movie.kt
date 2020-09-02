package com.example.kotlin_recyclerview

import android.util.Log


data class Movie(var name: String, var rating: Int,var price:Double,var qty:Int){


    fun getTotal(): Double? {
        var result :Double? = price * qty

        Log.i("Rajneesh","result $result")
        return result
    }


}