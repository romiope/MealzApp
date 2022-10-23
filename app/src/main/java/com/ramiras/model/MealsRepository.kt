package com.ramiras.model

import com.ramiras.model.api.MealsWebService
import com.ramiras.model.response.MealResponse
import com.ramiras.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    companion object {
        val instance: MealsRepository by lazy { MealsRepository(MealsWebService()) }
    }
}