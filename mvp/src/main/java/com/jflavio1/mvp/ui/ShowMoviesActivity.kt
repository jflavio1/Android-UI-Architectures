package com.jflavio1.mvp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.jflavio1.mvp.R
import com.jflavio1.mvp.entities.Movie
import com.jflavio1.mvp.presenter.ShowMoviesPresenter
import com.jflavio1.mvp.presenter.ShowMoviesPresenterImpl
import com.jflavio1.mvp.view.ShowMoviesView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class ShowMoviesActivity : AppCompatActivity() , ShowMoviesView {

    private lateinit var presenter: ShowMoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ShowMoviesPresenterImpl(this)
    }

    override fun setPresenter(presenter: ShowMoviesPresenter) {
        this.presenter = presenter
        btn.setOnClickListener {
            this.presenter.showMovies()
        }
    }

    override fun showLoader() {
        pb.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        pb.visibility = View.GONE
    }

    override fun errorInternet() {
        Toast.makeText(this, "Internet error!", Toast.LENGTH_SHORT).show()
    }

    override fun errorServer() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show()
    }

    override fun onMoviesLoaded(list: ArrayList<Movie>) {
        val text = StringBuilder()
        for (i in 0..(list.size -1)){
            text.append("Movie: ${list[i].name}, director: ${list[i].director}\n")
        }
        tv.text = text
    }

}
