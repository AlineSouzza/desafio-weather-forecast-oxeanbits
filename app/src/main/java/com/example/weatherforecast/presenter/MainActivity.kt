package com.example.weatherforecast.presenter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.model.HourlyModel
import com.example.weatherforecast.model.WeatherForecastModel
import com.example.weatherforecast.network.services
import com.example.weatherforecast.presenter.list.adapter.HourlyAdapter
import com.example.weatherforecast.presenter.search.SearchWeatherForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LocationListener {

    private val viewModel: SearchWeatherForecastViewModel by viewModels()
    private val serviceApi by lazy { services() }

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var binding: ActivityMainBinding
    private val locationPermissionCode = 2

    private var recyclerView: RecyclerView? = null
    private var hourlyList: ArrayList<Double> = ArrayList()
    lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getPermissionLocation()

        tvTemperature = binding.textNumberDegree
        recyclerView = binding.recyclerHourly
        val button: Button = binding.btnGetLocation
        button.setOnClickListener {
            getResponse()
        }

        hourlyAdapter = HourlyAdapter(hourlyList)

        val llm = LinearLayoutManager(this)
        recyclerView?.layoutManager = llm
        recyclerView?.adapter = hourlyAdapter
        binding.recyclerHourly.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getDataHourly(hourlyModel: HourlyModel) {

        hourlyList.clear()
        hourlyList.addAll(hourlyModel.temperature_2m)

        hourlyAdapter.notifyDataSetChanged()


    }

    private fun getResponse() {
        serviceApi.getWeatherForecast().enqueue(object : Callback<WeatherForecastModel> {
            override fun onResponse(
                call: Call<WeatherForecastModel>,
                response: Response<WeatherForecastModel>
            ) {
                if (response.isSuccessful) {
                    val weatherForecastModel = response.body()
                    tvTemperature.text =
                        weatherForecastModel?.hourly?.temperature_2m?.get(0).toString() + "°"
                    weatherForecastModel?.hourly?.let { getDataHourly(it) }
                }

            }

            override fun onFailure(call: Call<WeatherForecastModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getPermissionLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        tvGpsLocation = binding.textAboutClimate
        tvGpsLocation.text =
            "Latitude: " + location.latitude + " , Longitude: " + location.longitude
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                getPermissionLocation()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}