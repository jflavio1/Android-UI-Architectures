package com.jflavio1.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jflavio1.mvvm.R
import com.jflavio1.mvvm.entities.Movie
import com.jflavio1.mvvm.viewmodel.ShowMoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class ShowMoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: ShowMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ShowMoviesViewModel::class.java)

        viewModel.getState().observe(this, Observer<Int> {
            when (it) {
                1 -> {
                    pb.visibility = View.VISIBLE
                }

                2 -> {
                    pb.visibility = View.GONE
                }
            }
        })

        btn.setOnClickListener {
            viewModel.getMoviesList().observe(this@ShowMoviesActivity, Observer<ArrayList<Movie>> {
                onMoviesLoaded(it!!)
            })
        }

    }

    fun onMoviesLoaded(list: ArrayList<Movie>) {
        val text = StringBuilder()
        for (i in 0..(list.size - 1)) {
            text.append("Movie: ${list[i].name}, director: ${list[i].director}\n")
        }
        tv.text = text
    }

}
