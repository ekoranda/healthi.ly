package com.cs506.healthily.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs506.healthily.R
import com.cs506.healthily.data.model.JournalActivity
import com.cs506.healthily.view.activities.LogoutActivity
import com.cs506.healthily.view.activities.SignInActivity
import com.cs506.healthily.view.adapter.JournalAdapter
import com.cs506.healthily.viewModel.JournalViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var mGoogleSignInClient: GoogleSignInClient


/**
 * A simple [Fragment] subclass.
 * Use the [JournalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var rv: RecyclerView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View =  inflater.inflate(R.layout.fragment_journal, container, false)



//        val logoutBtn: Button = v.findViewById<Button>(R.id.btn_logout)
//        logoutBtn.setOnClickListener {
//            val intent = Intent(activity, LogoutActivity::class.java)
//            startActivity(intent)
//        }

        rv = v.findViewById(R.id.rv_activities)
        bindData()

        return v
    }

    private  fun setupRecyclerView(activities: List<JournalActivity>){
        rv!!.layoutManager = LinearLayoutManager(this.context)

        val adapter = JournalAdapter(activities)
        rv!!.adapter = adapter
    }



    private fun bindData() {
        val viewModel: JournalViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
            viewModel.getAllActivities()?.observe(viewLifecycleOwner) { mActivities ->
                Log.d("FIT", "HERE")
                setupRecyclerView(mActivities)
            }
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JournalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JournalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}