package com.jflavio1.mvc.model

import android.os.Handler
import android.os.Looper
import com.jflavio1.mvc.entities.Movie
import com.jflavio1.mvc.view.ShowMoviesView
import java.lang.Thread.sleep

/**
 * ShowMoviesModel
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesModel () {

    var list = arrayListOf<Movie>()

    fun requestDataFromServer(view: ShowMoviesView){
        Thread(Runnable {
            var mov = Movie("id1", "movie 1", "director1")
            list.add(mov)
            mov = Movie("id2", "movie 3", "director2")
            list.add(mov)
            mov = Movie("id3", "movie 3", "director3")
            list.add(mov)
            sleep(2000)
            Handler(Looper.getMainLooper()).post {
                view.requestMovies()
            }
        }).start()
    }

}