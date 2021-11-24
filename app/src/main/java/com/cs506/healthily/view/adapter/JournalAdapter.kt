package com.cs506.healthily.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs506.healthily.R
import com.cs506.healthily.data.model.JournalActivity

class JournalAdapter(private val mList: List<JournalActivity>) : RecyclerView.Adapter<JournalAdapter.ViewHolder>() {



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.journal_cardview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        val journalData = mList[position]

        holder.activityTextView.text = journalData.activity
        holder.dateTextView.text = journalData.date
        val hp = journalData.heartPoints
        val steps = journalData.stepCount
        if (hp != "null"){
            holder.heartPointTextView.text = "$hp Heart Points"
        }else{
            holder.heartPointTextView.text = "0 Heart Points"
        }
        if (steps != "null"){
            holder.stepCountTextView.text = "$steps Steps"
        }else{
            holder.stepCountTextView.text = "0 Steps"
        }





    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.tv_time)
        val activityTextView: TextView = itemView.findViewById(R.id.tv_activity_type)
        val stepCountTextView: TextView = itemView.findViewById(R.id.tv_step_count)
        val heartPointTextView: TextView = itemView.findViewById(R.id.tv_heart_points)



    }

}