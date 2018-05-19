package com.jflavio1.mvp.view

import com.jflavio1.mvp.entities.Movie
import com.jflavio1.mvp.presenter.ShowMoviesPresenter

/**
 * ShowMoviesView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
interface ShowMoviesView : BaseView{

    fun setPresenter(presenter: ShowMoviesPresenter)

    fun showLoader()

    fun hideLoader()

    fun errorInternet()

    fun errorServer()

    fun onMoviesLoaded(list: ArrayList<Movie>)

}