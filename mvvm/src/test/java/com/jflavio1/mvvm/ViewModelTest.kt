package com.jflavio1.mvvm

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jflavio1.mvvm.entities.Movie
import com.jflavio1.mvvm.repository.ShowMoviesRepository
import com.jflavio1.mvvm.viewmodel.ShowMoviesViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.reset

/**
 * ViewModelTest
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  21/5/17
 */
class ViewModelTest {

    // Android Components are executed in background
    // here we set to run synchronously on the same thread
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val repo = Mockito.mock(ShowMoviesRepository::class.java)

    val vm = ShowMoviesViewModel()

    lateinit var scheduler : TestScheduler

    @Before
    fun setUp() {
        reset(repo)
        scheduler = TestScheduler()
        vm.repo = repo
        vm.apply {
            computationScheduler = scheduler
            androidSchedulers = scheduler
        }
    }

    @Test
    fun loadStateTest() {
        whenever(repo.getMovies()).thenReturn(Observable.just(arrayListOf()))
        vm.getMoviesList()
        assertTrue(vm.getState().value == ShowMoviesViewModel.LOADING)
    }

    @Test
    fun emptyListTest() {
        whenever(repo.getMovies()).thenReturn(Observable.just(arrayListOf()))
        vm.getMoviesList()
        scheduler.triggerActions()
        assertTrue(vm.testList.value!!.size == 0)
    }

    @Test
    fun notEmptyListTest() {
        val list = arrayListOf<Movie>()
        list.add(Movie("id1", "m1", "d1"))
        list.add(Movie("id2", "m2", "d2"))
        whenever(repo.getMovies()).thenReturn(Observable.just(list))
        vm.getMoviesList()
        scheduler.triggerActions()
        assertTrue(vm.testList.value!!.size == 2)
    }
}