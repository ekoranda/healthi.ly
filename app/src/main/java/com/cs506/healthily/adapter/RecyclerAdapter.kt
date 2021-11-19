package com.cs506.healthily.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs506.healthily.R
import com.cs506.healthily.view.fragments.HomeFragment
import android.widget.BaseAdapter



class RecyclerAdapter:  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var listener: EventListener? = null
    var mFragment: HomeFragment? = null
    private val kode = arrayOf("Sample")
    private val kategori = arrayOf("Sample")
    private val isi = arrayOf("Sample")


    interface EventListener {
        fun onEvent()
    }

    fun CustomAdapter(listener: EventListener?) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemCode: TextView
        var itemCategory : TextView
        var itemIs: TextView

        init {
            itemCode = itemView.findViewById(R.id.Code)
            itemCategory = itemView.findViewById(R.id.Category)
            itemIs = itemView.findViewById(R.id.itemIs)

            itemView.setOnClickListener {
                listener?.onEvent();
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemCode.text = kode[i]
        viewHolder.itemCategory.text = kategori[i]
        viewHolder.itemIs.text = isi[i]

    }
    override fun getItemCount(): Int {
        return kode.size
    }

}
