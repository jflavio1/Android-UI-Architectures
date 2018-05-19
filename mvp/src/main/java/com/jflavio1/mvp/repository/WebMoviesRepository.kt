package com.jflavio1.mvp.repository

import com.jflavio1.mvp.entities.Movie
import io.reactivex.Observable
import java.lang.Thread.sleep

/**
 * WebMoviesRepository
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class WebMoviesRepository : MoviesRepository<Movie> {

    // This method is called from a background thread using Schedulers.computation()
    override fun getMovies(): Observable<ArrayList<Movie>> {
        return Observable.create<ArrayList<Movie>> {
            val list = arrayListOf<Movie>()

            // we sleep the thread where this task is executed: Schedulers.computation()
            sleep(5000)

            var mov = Movie("id1", "movie 1", "director1")
            list.add(mov)
            mov = Movie("id2", "movie 3", "director2")
            list.add(mov)
            mov = Movie("id3", "movie 3", "director3")
            list.add(mov)

            // after the task is complete...
            it.onNext(list)
            it.onComplete()
        }
    }
}