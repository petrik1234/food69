package com.example.food69.viewmodel

import androidx.lifecycle.ViewModel
import com.example.food69.data.FoodItem
import com.example.food69.data.FoodRepository

class FoodViewModel : ViewModel() {
    private val repository = FoodRepository()

    val foods = repository.foods
    val totalCalories = repository.totalCalories
    val totalProteins = repository.totalProteins
    val totalCarbs = repository.totalCarbs
    val totalFats = repository.totalFats

    fun addFood(food: FoodItem) {
        repository.addFood(food)
    }

    fun removeFood(foodId: Int) {
        repository.removeFood(foodId)
    }
}