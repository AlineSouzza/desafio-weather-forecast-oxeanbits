package com.example.weatherforecast.presenter.list.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.model.HourDataModel
import com.example.weatherforecast.util.DateUtils
import kotlin.math.roundToInt

class HourlyAdapter(private val hourlyList: List<HourDataModel>) :
    RecyclerView.Adapter<HourlyAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
        return ViewHolderClass(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = hourlyList[position]
        holder.textTemperature.text = currentItem.temperature_2m.roundToInt().toString() + "°"
        holder.textHour.text = DateUtils().formatLocalDateToTime(DateUtils().stringToDate(currentItem.time));
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTemperature: TextView = itemView.findViewById(R.id.text_temperature)
        val textHour: TextView = itemView.findViewById(R.id.text_hour)
    }
}
