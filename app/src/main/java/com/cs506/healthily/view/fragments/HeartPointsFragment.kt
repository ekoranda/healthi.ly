package com.cs506.healthily.view.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.cs506.healthily.R
import com.cs506.healthily.data.model.DayHeart
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.repository.DailStepsRepository
import com.cs506.healthily.viewModel.DailyHeartPointsViewModel
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import java.lang.NumberFormatException
import java.lang.reflect.Array.set
import java.time.LocalDate
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeartPointsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeartPointsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindData()

        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_heart_points, container, false)





        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HeartPointsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HeartPointsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupGraphView(days: List<DayHeart>) {


        val series: BarGraphSeries<DataPoint> = BarGraphSeries(
            arrayOf(
                days[0].heartPoints?.let { DataPoint(1.0, it.toDouble()) },
                days[1].heartPoints?.let { DataPoint(2.0, it.toDouble()) },
                days[2].heartPoints?.let { DataPoint(3.0, it.toDouble()) },
                days[3].heartPoints?.let { DataPoint(4.0, it.toDouble()) },
                days[4].heartPoints?.let { DataPoint(5.0, it.toDouble()) },
                days[5].heartPoints?.let { DataPoint(6.0, it.toDouble()) },
                days[6].heartPoints?.let { DataPoint(7.0, it.toDouble()) },

                )
        )
        val graph = view?.findViewById(R.id.graph) as GraphView
        graph.addSeries(series)
        series.setSpacing(50)
        graph.getGridLabelRenderer().horizontalAxisTitle = "Days Since Current Time"
        graph.getGridLabelRenderer().verticalAxisTitle = "Heart Points Earned"


    }

    private fun setUpCurrentProgress(days: List<DayHeart>){
        val progressBar: ProgressBar? = view?.findViewById(R.id.progress_bar)
        val progressText: TextView? = view?.findViewById(R.id.progress_text)
        val errorText: TextView? = view?.findViewById(R.id.error_text)
        var totalSteps = 0
        for (day in days) {
            if (day.day == "7"){
                totalSteps += day.heartPoints?.toInt()!!
            }

        }






        val currentProgress = totalSteps
        var stepGoal: Int? = 0
        var fitAccess = true
        try {
            stepGoal = days[0].heartGoal?.toInt()
        } catch (e:NumberFormatException) {
            stepGoal = 1
            fitAccess = false
        }
        if (progressText != null) {
            if (fitAccess) {
                progressText.text = "" + currentProgress + " / " + stepGoal
            } else {
                progressText.text = "N/A"
                if (errorText != null) {
                    errorText.text = "Login to the Google Fit app to view data"
                }
            }
        }
        val progressPercentage = 100 * currentProgress / stepGoal!!
        if (progressBar != null) {
            progressBar.setProgress(progressPercentage)
            var color = 0xffff0000
            if(currentProgress < stepGoal){
            }else{
                color = 0xFF4DCE47
                progressBar.getIndeterminateDrawable().setColorFilter(color.toInt(), PorterDuff.Mode.SRC_IN);
                progressBar.getProgressDrawable().setColorFilter(color.toInt(), PorterDuff.Mode.SRC_IN);
            }






        }
    }


    private fun bindData(){
        val viewModel: DailyHeartPointsViewModel =
            ViewModelProviders.of(this).get(DailyHeartPointsViewModel::class.java)
        viewModel.getAllDays()?.observe(viewLifecycleOwner) { mDays ->
            setupGraphView(mDays)
            setUpCurrentProgress(mDays)
        }


    }
}