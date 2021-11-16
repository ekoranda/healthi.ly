package com.cs506.healthily.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.cs506.healthily.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

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
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_heart_points, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val progressText: TextView = view.findViewById(R.id.progress_text)
        val currentProgress = 15.3
        val stepGoal = 21
        progressText.text = "" + currentProgress + " / " + stepGoal
        val progressPercentage = 100 * currentProgress / stepGoal
        progressBar.setProgress(progressPercentage.toInt())


        val graph = view.findViewById(R.id.graph) as GraphView
        val series: BarGraphSeries<DataPoint> = BarGraphSeries(
            arrayOf(
                DataPoint(-7.0, 12.0),
                DataPoint(-6.0, 7.2),
                DataPoint(-5.0, 23.4),
                DataPoint(-4.0, 22.1),
                DataPoint(-3.0, 19.2),
                DataPoint(-2.0, 16.0),
                DataPoint(-1.0, 17.8),
                DataPoint(0.0, currentProgress),
            )
        )
        graph.addSeries(series)
        series.setSpacing(50)

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
}