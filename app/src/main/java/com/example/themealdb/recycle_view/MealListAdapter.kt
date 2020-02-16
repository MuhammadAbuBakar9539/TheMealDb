package com.example.themealdb.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themealdb.R
import com.example.themealdb.model.MealListResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_item.view.*

class MealListAdapter(
    private var mealList: MealListResponse,
    private val onRecycleItemClicked: OnRecycleItemClicked
) :
    RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meal_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mealList.categories.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        Picasso.get().load(mealList.categories[position].strCategoryThumb).into(holder.mealThumb)
        holder.name.text = mealList.categories[position].strCategory
        holder.description.text = mealList.categories[position].strCategoryDescription

        holder.bind(mealList.categories[position].strCategory)
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealThumb: ImageView = itemView.img_mealThumb
        val name: TextView = itemView.tv_mealName
        val description: TextView = itemView.tv_categoryDescription

        fun bind(category: String) {
            itemView.setOnClickListener { onRecycleItemClicked.onRecycleItemClicked(category) }
        }
    }

}