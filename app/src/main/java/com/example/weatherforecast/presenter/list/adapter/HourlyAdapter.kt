package com.example.weatherforecast.presenter.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R

class HourlyAdapter(private val hourlyList: List<Double>) :
    RecyclerView.Adapter<HourlyAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = hourlyList[position]
        holder.text.text = currentItem.toString()
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text_temperature)
    }

    fun listHourly(listHourly: List<Double>): Int {
        return listHourly.size
    }
}
