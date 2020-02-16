package com.example.themealdb.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.MealCategoryResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_item.view.*

class MealCategoryAdapter(private val mealCategoryList: MealCategoryResponse) :
    RecyclerView.Adapter<MealCategoryAdapter.MealCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder {
        return MealCategoryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_category_items, parent, false))
    }

    override fun getItemCount(): Int {
        return mealCategoryList.meals.size
    }

    override fun onBindViewHolder(holder: MealCategoryViewHolder, position: Int) {
        holder.mealName.text = mealCategoryList.meals[position].strMeal
        Picasso.get().load(mealCategoryList.meals[position].strMealThumb).into(holder.mealThumb)
    }

    class MealCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealName: TextView = itemView.tv_mealName
        val mealThumb: ImageView = itemView.img_mealThumb
    }

}