package com.jflavio1.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jflavio1.mvvm.entities.Movie
import com.jflavio1.mvvm.repository.ShowMoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ShowMoviesViewModel
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesViewModel : ViewModel() {

    private val list = MutableLiveData<ArrayList<Movie>>()
    private val loadState = MutableLiveData<Int>()
    private var repo = ShowMoviesRepository()

    fun getState() = loadState

    fun getMoviesList(): MutableLiveData<ArrayList<Movie>> {
        loadState.value = 1
        repo.getMovies()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadList)
        return list
    }

    private fun loadList(l: ArrayList<Movie>) {
        loadState.value = 2
        list.postValue(l)
    }

}