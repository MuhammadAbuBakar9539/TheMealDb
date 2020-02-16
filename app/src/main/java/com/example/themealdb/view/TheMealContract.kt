package com.example.themealdb.view

import com.example.themealdb.model.MealCategoryResponse
import com.example.themealdb.model.MealListResponse

interface TheMealContract {
    interface MealListView {
        fun showMealList(mealListResponse: MealListResponse)
        fun showError(errorMessage: String)
    }

    interface MealCategoryView{
        fun showMealCategory(mealCategoryResponse: MealCategoryResponse)
        fun showError(errorMessage: String)
    }

    interface MealListPresenter{
        fun getMealList()
        fun onDestroyCalled()
    }

    interface MealCategoryPresenter{
        fun getMealCategory(categoryName:String)
        fun onDestroyCalled()
    }
}