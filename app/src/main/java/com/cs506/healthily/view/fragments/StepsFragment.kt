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
import androidx.recyclerview.widget.LinearLayoutManager

import com.cs506.healthily.R
import com.cs506.healthily.data.model.DaySteps
import com.cs506.healthily.data.model.Goals
import com.cs506.healthily.data.repository.GoalsRepository
import com.cs506.healthily.view.adapter.DayStepAdapter
import com.cs506.healthily.viewModel.DayStepsViewModel
import com.cs506.healthily.viewModel.goalViewModel
import com.google.firebase.auth.FirebaseAuth
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

//import android.R

import com.jjoe64.graphview.GraphView




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StepsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StepsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
   // var totalSteps=0
    //var stepGoal: Int =0



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
        val view:View = inflater.inflate(R.layout.fragment_steps, container, false)






        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StepsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StepsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupGraphView(days: List<DaySteps>) {


        val series: BarGraphSeries<DataPoint> = BarGraphSeries(
            arrayOf(
                days[0].steps?.let { DataPoint(1.0, it.toDouble()) },
                days[1].steps?.let { DataPoint(2.0, it.toDouble()) },
                days[2].steps?.let { DataPoint(3.0, it.toDouble()) },
                days[3].steps?.let { DataPoint(4.0, it.toDouble()) },
                days[4].steps?.let { DataPoint(5.0, it.toDouble()) },
                days[5].steps?.let { DataPoint(6.0, it.toDouble()) },
                days[6].steps?.let { DataPoint(7.0, it.toDouble()) },

            )
        )
        val graph = view?.findViewById(R.id.graph) as GraphView
        graph.addSeries(series)
        series.setSpacing(50)
        graph.getGridLabelRenderer().horizontalAxisTitle = "Days Since Current Time"
        graph.getGridLabelRenderer().verticalAxisTitle = "Step Count Achieved"


    }

    private fun setUpCurrentProgress(days: List<DaySteps>){
        val progressBar: ProgressBar? = view?.findViewById(R.id.progress_bar)
        val progressText: TextView? = view?.findViewById(R.id.progress_text)
        var totalSteps = 0
        for (day in days) {
            totalSteps += day.steps?.toInt()!!
        }


        val currentProgress = totalSteps / 7 // TODO: Dont we want to compare total steps in a week to step goals (of a week)?
        val stepGoal = days[0].stepGoal?.toInt()
        if (progressText != null) {
            progressText.text = "" + currentProgress + " / " + stepGoal
        }
        val progressPercentage = 100 * currentProgress / stepGoal!!
        if (progressBar != null) {
            progressBar.setProgress(progressPercentage)
            var color = 0xffff0000
            if(totalSteps < stepGoal){
            }else{
                color = 0xFF4DCE47
                progressBar.getIndeterminateDrawable().setColorFilter(color.toInt(), PorterDuff.Mode.SRC_IN);
                progressBar.getProgressDrawable().setColorFilter(color.toInt(), PorterDuff.Mode.SRC_IN);
            }


        }
    }


    private fun bindData(){
        val viewModel: DayStepsViewModel =
            ViewModelProviders.of(this).get(DayStepsViewModel::class.java)
            viewModel.getAllDays()?.observe(viewLifecycleOwner) { mDays ->
                setupGraphView(mDays)
                setUpCurrentProgress(mDays)

        }


    }
}