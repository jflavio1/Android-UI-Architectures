package com.jflavio1.mvc.controller

import com.jflavio1.mvc.model.ShowMoviesModel
import com.jflavio1.mvc.view.ShowMoviesView

/**
 * ShowMoviesController
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  19/5/17
 */
class ShowMoviesController(var view: ShowMoviesView, var model: ShowMoviesModel) {

    fun onShowMoviesBtnClick(){
        model.requestDataFromServer(view)
    }

}