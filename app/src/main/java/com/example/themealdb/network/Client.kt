package com.example.themealdb.network

import com.example.themealdb.common.CATEGORY
import com.example.themealdb.common.MEAL_LIST_Cat_END_POINT
import com.example.themealdb.common.MEAL_LIST_END_POINT
import com.example.themealdb.model.MealCategoryResponse
import com.example.themealdb.model.MealListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {
    @GET(MEAL_LIST_END_POINT)
    fun getMealList(): Observable<MealListResponse>

    @GET(MEAL_LIST_Cat_END_POINT)
    fun getCategoryMeal(@Query(CATEGORY) categoryName: String): Observable<MealCategoryResponse>
}