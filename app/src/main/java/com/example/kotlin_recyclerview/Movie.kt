package com.example.kotlin_recyclerview

import android.util.Log


data class Movie(var name: String, var rating: Int,var price:Double,var qty:Int){


    fun getTotal(): Double? {
        val result :Double? = price * qty
        return result
    }


}