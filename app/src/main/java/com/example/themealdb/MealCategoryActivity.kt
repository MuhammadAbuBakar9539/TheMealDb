package com.example.themealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealdb.common.INTENT_KEY
import com.example.themealdb.model.MealCategoryResponse
import com.example.themealdb.presenter.MealCategoryPresenter
import com.example.themealdb.recycle_view.MealCategoryAdapter
import com.example.themealdb.view.TheMealContract
import kotlinx.android.synthetic.main.activity_meal_category.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MealCategoryActivity : AppCompatActivity(), TheMealContract.MealCategoryView {
    private lateinit var mealCategoryPresenter: TheMealContract.MealCategoryPresenter
    private lateinit var categoryName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_category)

        categoryName = intent.getStringExtra(INTENT_KEY)

        title = categoryName

        mealCategoryPresenter = MealCategoryPresenter(this)
        mealCategoryPresenter.getMealCategory(categoryName)
    }

    override fun showMealCategory(mealCategoryResponse: MealCategoryResponse) {
        rv_meal_category.layoutManager = LinearLayoutManager(this)
        val mealCategoryAdapter = MealCategoryAdapter(mealCategoryResponse)
        pb_meal_cat_progress_bar.visibility = View.GONE
        rv_meal_category.adapter = mealCategoryAdapter
    }

    override fun showError(errorMessage: String) {
        pb_meal_cat_progress_bar.visibility = View.GONE
        lil_meal_cat_error.visibility = View.VISIBLE
        tv_meal_cat_errorMessage.text = errorMessage

        btn_cat_meal_retry.setOnClickListener {
            lil_meal_cat_error.visibility = View.GONE
            pb_meal_cat_progress_bar.visibility = View.VISIBLE
            mealCategoryPresenter.getMealCategory(categoryName)
        }
    }

    override fun onDestroy() {
        mealCategoryPresenter.onDestroyCalled()
        super.onDestroy()
    }
}
