package com.ramiras.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ramiras.model.MealsRepository
import com.ramiras.model.response.MealResponse

class MealDetailsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.instance
    val mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""
        mealState.value = repository.getMeal(mealId)
    }
}