package com.ramiras.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramiras.model.MealsRepository
import com.ramiras.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealCategoriesViewModel(private val repository: MealsRepository = MealsRepository.instance) :
    ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}