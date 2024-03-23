package com.example.weatherforecast.presenter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.model.CapitalModel
import com.example.weatherforecast.model.HourDataModel
import com.example.weatherforecast.model.WeatherForecastModel
import com.example.weatherforecast.network.services
import com.example.weatherforecast.presenter.list.adapter.HourlyAdapter
import com.example.weatherforecast.util.CapitalUtils
import com.example.weatherforecast.util.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LocationListener, AdapterView.OnItemSelectedListener {
    private val serviceApi by lazy { services() }

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationManager: LocationManager
    private lateinit var scrollView: ScrollView
    private lateinit var temperature: TextView
    private lateinit var indexUv: TextView
    private lateinit var relativeHumidity: TextView
    private lateinit var precipitation: TextView
    private lateinit var sunrise: TextView
    private lateinit var sunset: TextView
    private lateinit var dayNightImg: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var hourlyAdapter: HourlyAdapter

    private val locationPermissionCode = 2
    private var hourDataList: ArrayList<HourDataModel> = ArrayList()
    private var capitalArray: ArrayList<CapitalModel> = ArrayList()
    private var spinnerLocationLabels: ArrayList<String> = ArrayList()
    private var currentLatitude: Double? = null
    private var currentLongitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        scrollView = binding.scrollView
        temperature = binding.temperature
        indexUv = binding.indexUv
        relativeHumidity = binding.relativeHumidity
        precipitation = binding.precipitation
        dayNightImg = binding.dayNightImg
        sunrise = binding.sunrise
        sunset = binding.sunset
        recyclerView = binding.recyclerHourly

        hourlyAdapter = HourlyAdapter(hourDataList)

        val llm = LinearLayoutManager(this)
        recyclerView.layoutManager = llm
        recyclerView.adapter = hourlyAdapter
        binding.recyclerHourly.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        setupSpinner()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateScreen(weatherForecastModel: WeatherForecastModel) {
        temperature.text =
            weatherForecastModel.current.temperature_2m?.roundToInt().toString() + "°"
        indexUv.text = weatherForecastModel.daily.uv_index_max?.first().toString()
        relativeHumidity.text = weatherForecastModel.current.relative_humidity_2m.toString() + "%"
        precipitation.text = weatherForecastModel.current.precipitation?.toString() + "mm"

        if (weatherForecastModel.current.is_day == 1) {
            scrollView.setBackgroundColor(Color.parseColor("#81CAEC"))
            dayNightImg.setImageDrawable(getDrawable(R.drawable.image_sun_64x))
        } else {
            dayNightImg.setImageDrawable(getDrawable(R.drawable.moon_svg))
            scrollView.setBackgroundColor(Color.parseColor("#808080"))
        }

        sunrise.text = DateUtils().formatLocalDateToTime(
            DateUtils().stringToDate(weatherForecastModel.daily.sunrise.first())
        )
        sunset.text = DateUtils().formatLocalDateToTime(
            DateUtils().stringToDate(weatherForecastModel.daily.sunset.first())
        )

        hourDataList.clear()

        val calendar = Calendar.getInstance()
        val initialIndex = calendar.get(Calendar.HOUR_OF_DAY)

        for (i in 0..24) {
            hourDataList.add(
                HourDataModel(
                    weatherForecastModel.hourly.temperature_2m[initialIndex + i],
                    weatherForecastModel.hourly.time[initialIndex + i],
                )
            )
        }

        hourlyAdapter.notifyDataSetChanged()
    }

    private fun setupSpinner() {
        spinnerLocationLabels.add("Localização atual")

        capitalArray = CapitalUtils().getCapitalsData()
        for (capital in capitalArray) {
            spinnerLocationLabels.add(capital.nome)
        }

        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLocationLabels)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_left_item)

        with(binding.spinnerBrazilianCapitals) {
            adapter = arrayAdapter
            onItemSelectedListener = this@MainActivity
        }

    }

    private fun getWeatherForecastData(latitude: Double, longitude: Double) {
        serviceApi.getWeatherForecast(latitude, longitude)
            .enqueue(object : Callback<WeatherForecastModel> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<WeatherForecastModel>,
                    response: Response<WeatherForecastModel>
                ) {
                    if (response.isSuccessful) {
                        val weatherForecastModel = response.body()
                        if (weatherForecastModel != null) {
                            populateScreen(weatherForecastModel)
                        }
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
        if (binding.spinnerBrazilianCapitals.selectedItemPosition == 0) {
            currentLatitude = location.latitude
            currentLongitude = location.longitude
            getWeatherForecastData(location.latitude, location.longitude)
        }
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

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0){
            showToast(message = "Previsão do tempo para ${spinnerLocationLabels[position]}")
            if (currentLatitude != null && currentLongitude != null) {
                getWeatherForecastData(currentLatitude!!, currentLongitude!!)
            } else {
                getPermissionLocation()
            }
        } else {
            showToast(message = "Previsão do tempo para ${spinnerLocationLabels[position]}")
            getWeatherForecastData(capitalArray[position-1].latitude, capitalArray[position-1].longitude)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        showToast(message = "Nada selecionado")
    }

    private fun showToast(
        context: Context = applicationContext,
        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()
    }
}