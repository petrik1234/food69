package com.example.food69.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FoodRepository {
    private val _foods = mutableStateListOf<FoodItem>()
    private val _totalCalories = MutableStateFlow(0)
    private val _totalProteins = MutableStateFlow(0.0)
    private val _totalCarbs = MutableStateFlow(0.0)
    private val _totalFats = MutableStateFlow(0.0)

    val foods: List<FoodItem> = _foods
    val totalCalories: StateFlow<Int> = _totalCalories.asStateFlow()
    val totalProteins: StateFlow<Double> = _totalProteins.asStateFlow()
    val totalCarbs: StateFlow<Double> = _totalCarbs.asStateFlow()
    val totalFats: StateFlow<Double> = _totalFats.asStateFlow()

    init {
        // Добавляем примеры еды
        addFood(FoodItem(1, "Овсянка", 350, 12.0, 60.0, 6.0, 100))
        addFood(FoodItem(2, "Банан", 89, 1.1, 23.0, 0.3, 120))
        addFood(FoodItem(3, "Куриная грудка", 165, 31.0, 0.0, 3.6, 100))
    }

    fun addFood(food: FoodItem) {
        val newFood = food.copy(id = _foods.size + 1)
        _foods.add(newFood)
        updateTotals()
    }

    fun removeFood(foodId: Int) {
        _foods.removeAll { it.id == foodId }
        updateTotals()
    }

    private fun updateTotals() {
        _totalCalories.value = _foods.sumOf { it.calories }
        _totalProteins.value = _foods.sumOf { it.proteins }
        _totalCarbs.value = _foods.sumOf { it.carbs }
        _totalFats.value = _foods.sumOf { it.fats }
    }
}