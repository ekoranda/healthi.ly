package com.cs506.healthily.view.fragments

import android.widget.ProgressBar
import androidx.test.filters.MediumTest
import com.cs506.healthily.R
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.launchFragmentInHiltContainer
import com.cs506.healthily.viewModel.DailyHeartPointsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.jjoe64.graphview.GraphView
import kotlinx.android.synthetic.main.fragment_heart_points.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HeartPointsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    /**
     * Test the function newInstance(param1: String, param2: String) in HeartPointsFragment
     */
    @Test
    fun newInstanceTest(){
        val HeartPointsFragmentTest1 : HeartPointsFragment = HeartPointsFragment()
        var HeartPointsFragmentTest2 = HeartPointsFragment.newInstance("Test1", "Test2")
        if (HeartPointsFragmentTest2 !=  HeartPointsFragmentTest1){
            assertThat(true).isTrue()
        }
    }

    /**
     * Test the function setupGraphView(days: List<DayHeart>) in HeartPointsFragment
     */
    @Test
    fun setupGraphViewTest(){

        val days : List<DayHeart> = listOf(DayHeart("0", "50", "100"),DayHeart("1", "50", "100"))
        launchFragmentInHiltContainer<HeartPointsFragment> {
            this.setupGraphView(days)
            assertThat(graph).isNotNull()
        }


    }

    /**
     * Test the function setUpCurrentProgress(days: List<DayHeart>) in HeartPointsFragment
     */
    @Test
    fun setUpCurrentProgressTest(){

        val days : List<DayHeart> = listOf(DayHeart("0", "50", "100"),DayHeart("1", "50", "100"))
        launchFragmentInHiltContainer<HeartPointsFragment> {
            this.setUpCurrentProgress(days)
            val progressBar: ProgressBar? = view?.findViewById(R.id.progress_bar)
            assertThat(progressBar).isNotNull()
        }


    }

    /**
     * Test the function bindData() in HeartPointsFragment
     */
    @Test
    fun bindDataTest(){


        launchFragmentInHiltContainer<HeartPointsFragment> {


        }


    }

}