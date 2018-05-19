package com.jflavio1.mvp.presenter

import com.jflavio1.mvp.entities.Movie
import com.jflavio1.mvp.model.ShowMoviesInteractor

/**
 * ShowMoviesPresenter
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
interface ShowMoviesPresenter : BasePresenter {

    fun setInteractor(interactor: ShowMoviesInteractor)

    fun showMovies()

    fun onMoviesLoaded(list: ArrayList<Movie>)

    fun onMoviesError(errorCode: Int)

}