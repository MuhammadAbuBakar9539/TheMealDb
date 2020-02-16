package com.example.themealdb.model


import com.google.gson.annotations.SerializedName

data class MealListResponse(
    @SerializedName("categories")
    val categories: List<Category>
)