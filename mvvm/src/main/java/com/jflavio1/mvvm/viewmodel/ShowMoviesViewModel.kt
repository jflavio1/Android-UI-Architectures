package com.jflavio1.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.RestrictTo
import com.jflavio1.mvvm.entities.Movie
import com.jflavio1.mvvm.repository.ShowMoviesRepository
import io.reactivex.Scheduler

/**
 * ShowMoviesViewModel
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesViewModel : ViewModel() {

    private val list = MutableLiveData<ArrayList<Movie>>()

    @RestrictTo(RestrictTo.Scope.TESTS)
    val testList = MutableLiveData<ArrayList<Movie>>()

    private val loadState = MutableLiveData<Int>()
    var repo = ShowMoviesRepository()
    lateinit var computationScheduler : Scheduler
    lateinit var androidSchedulers: Scheduler

    fun getState() = loadState

    fun getMoviesList(): MutableLiveData<ArrayList<Movie>> {
        loadState.value = LOADING
        repo.getMovies()
                .subscribeOn(computationScheduler)
                .observeOn(androidSchedulers)
                .subscribe(this::loadList)
        return list
    }

    private fun loadList(l: ArrayList<Movie>) {
        loadState.value = LOADED
        list.postValue(l)
        testList.postValue(l)
    }

    companion object {
        const val LOADING = 1
        const val LOADED = 2
    }

}