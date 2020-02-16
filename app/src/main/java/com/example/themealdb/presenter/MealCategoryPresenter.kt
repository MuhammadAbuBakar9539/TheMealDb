package com.example.themealdb.presenter

import com.example.themealdb.network.ClientInstance
import com.example.themealdb.view.TheMealContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealCategoryPresenter(private var view: TheMealContract.MealCategoryView?) :
    TheMealContract.MealCategoryPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getMealCategory(categoryName: String) {
        val call = ClientInstance.getClient().getCategoryMeal(categoryName)
        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mealCategory -> view?.showMealCategory(mealCategory) },
                    { errorMessage -> view?.showError(errorMessage = errorMessage.message.toString()) })
        )
    }

    override fun onDestroyCalled() {
        compositeDisposable.clear()
        view = null
    }
}