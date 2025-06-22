package com.example.food69.data

data class FoodItem(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val proteins: Double,
    val carbs: Double,
    val fats: Double,
    val weight: Int, // в граммах
    val timestamp: Long = System.currentTimeMillis()
)