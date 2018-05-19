package com.jflavio1.mvp.repository

/**
 * MoviesRepository
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
interface MoviesRepository<T> {

    fun getMovies() : ArrayList<T>

}