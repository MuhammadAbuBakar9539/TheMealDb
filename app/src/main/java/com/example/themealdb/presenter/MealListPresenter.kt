package com.example.themealdb.presenter

import com.example.themealdb.network.ClientInstance
import com.example.themealdb.view.TheMealContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealListPresenter(private var view: TheMealContract.MealListView?) :
    TheMealContract.MealListPresenter {
    private val call = ClientInstance.getClient().getMealList()
    private val compositeDisposable = CompositeDisposable()

    override fun getMealList() {
        compositeDisposable.add(
            call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mealList -> view?.showMealList(mealList) },
                    { errorMessage -> view?.showError(errorMessage = errorMessage.toString()) })
        )
    }

    override fun onDestroyCalled() {
        compositeDisposable.clear()
        view = null
    }
}