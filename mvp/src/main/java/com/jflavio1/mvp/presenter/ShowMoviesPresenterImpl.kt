package com.jflavio1.mvp.presenter

import com.jflavio1.mvp.entities.Movie
import com.jflavio1.mvp.model.ShowMoviesInteractor
import com.jflavio1.mvp.model.ShowMoviesInteractorImpl
import com.jflavio1.mvp.view.ShowMoviesView

/**
 * ShowMoviesPresenterImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesPresenterImpl(var view: ShowMoviesView) : ShowMoviesPresenter{

    private lateinit var interactor: ShowMoviesInteractor

    init {
        ShowMoviesInteractorImpl(this)
        this.view.setPresenter(this)
    }

    override fun setInteractor(interactor: ShowMoviesInteractor) {
        this.interactor = interactor
    }

    override fun showMovies() {
        view.showLoader()
        this.interactor.getMovies()
    }

    override fun onMoviesLoaded(list: ArrayList<Movie>) {
        view.hideLoader()
        view.onMoviesLoaded(list)
    }

    override fun onMoviesError(errorCode: Int) {
        view.hideLoader()
    }

}