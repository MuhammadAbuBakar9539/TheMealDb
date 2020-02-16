package com.example.themealdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealdb.common.INTENT_KEY
import com.example.themealdb.model.MealListResponse
import com.example.themealdb.presenter.MealListPresenter
import com.example.themealdb.recycle_view.MealListAdapter
import com.example.themealdb.recycle_view.OnRecycleItemClicked
import com.example.themealdb.view.TheMealContract
import kotlinx.android.synthetic.main.activity_the_meal.*

class TheMealActivity : AppCompatActivity(),TheMealContract.MealListView {
    private lateinit var mealListPresenter: TheMealContract.MealListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_meal)

        title = getString(R.string.meal_categories)

        mealListPresenter = MealListPresenter(this)
        mealListPresenter.getMealList()
    }

    override fun showMealList(mealListResponse: MealListResponse) {
        rv_meal_list.layoutManager = LinearLayoutManager(this)
        val mealListAdapter = MealListAdapter(mealListResponse,object :OnRecycleItemClicked{
            override fun onRecycleItemClicked(category: String?) {
                val intent = Intent(this@TheMealActivity, MealCategoryActivity::class.java)
                intent.putExtra(INTENT_KEY,category)
                startActivity(intent)
            }

        })
        pb_meal_progress_bar.visibility = View.GONE
        rv_meal_list.adapter = mealListAdapter
    }

    override fun showError(errorMessage: String) {
        pb_meal_progress_bar.visibility = View.GONE
        lil_meal_error.visibility = View.VISIBLE
        tv_meal_errorMessage.text = errorMessage

        btn_meal_retry.setOnClickListener {
            lil_meal_error.visibility = View.GONE
            pb_meal_progress_bar.visibility = View.VISIBLE
            mealListPresenter.getMealList()
        }
    }

    override fun onDestroy() {
        mealListPresenter.onDestroyCalled()
        super.onDestroy()
    }
}
