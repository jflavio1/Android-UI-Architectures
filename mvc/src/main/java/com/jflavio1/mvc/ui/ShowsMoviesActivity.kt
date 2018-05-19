package com.jflavio1.mvc.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jflavio1.mvc.R
import com.jflavio1.mvc.controller.ShowMoviesController
import com.jflavio1.mvc.model.ShowMoviesModel
import com.jflavio1.mvc.view.ShowMoviesView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class ShowsMoviesActivity : AppCompatActivity(), ShowMoviesView {

    private lateinit var controller : ShowMoviesController
    private lateinit var model: ShowMoviesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ShowMoviesModel()
        controller = ShowMoviesController(this, model)

        btn.setOnClickListener {
            controller.onShowMoviesBtnClick()
        }

    }

    override fun requestMovies() {
        val text = StringBuilder()
        for (i in 0..(model.list.size -1)){
            text.append("Movie: ${model.list[i].name}, director: ${model.list[i].director}\n")
        }
        tv.text = text
    }

    override fun requestError() {
        Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show()
    }

}
