package com.furkanreyhan.amphibians.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.furkanreyhan.amphibians.R
import com.furkanreyhan.amphibians.remote.model.AmphibiansItem

class AmphibianAdapter (var amphibians: List<AmphibiansItem>, val activity: Activity)
    : RecyclerView.Adapter<AmphibianAdapter.ViewHolder>(){

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.name)
        val txtType = itemView.findViewById<TextView>(R.id.type)
        val txtDescription = itemView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return amphibians.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val amphibian : AmphibiansItem = amphibians[position]

        holder.txtName.setText(amphibian.name)
        holder.txtType.setText(amphibian.type)
        holder.txtDescription.setText(amphibian.description)
    }

}
