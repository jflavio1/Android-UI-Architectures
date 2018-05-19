package com.jflavio1.mvp.model

import com.jflavio1.mvp.entities.Movie
import com.jflavio1.mvp.presenter.ShowMoviesPresenter
import com.jflavio1.mvp.repository.MoviesRepository
import com.jflavio1.mvp.repository.WebMoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ShowMoviesInteractorImpl
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesInteractorImpl(var presenter: ShowMoviesPresenter): ShowMoviesInteractor {

    private var repo: MoviesRepository<Movie> = WebMoviesRepository()

    init {
        this.presenter.setInteractor(this)
    }

    override fun getMovies() {
        repo.getMovies()
                // in what thread is going to be done the work
                .subscribeOn(Schedulers.computation())
                // in what thread is going to be listened every onNext()
                .observeOn(AndroidSchedulers.mainThread())
                // onNext() call will execute onMoviesLoaded method
                .subscribe({ presenter.onMoviesLoaded(it) })
    }



}