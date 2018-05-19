package com.jflavio1.mvp.repository

import io.reactivex.Observable

/**
 * MoviesRepository
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
interface MoviesRepository<T> {

    fun getMovies() : Observable<ArrayList<T>>

}