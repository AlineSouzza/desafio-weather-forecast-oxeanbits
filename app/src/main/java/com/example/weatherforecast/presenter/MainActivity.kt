package com.example.weatherforecast.presenter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
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
class MainActivity : ComponentActivity(), LocationListener, AdapterView.OnItemSelectedListener {

    var brazilianCapitals = arrayOf(
        "Aracaju",
        "Belém",
        "Belo Horizonte",
        "Boa Vista",
        "Brasília *",
        "Campo Grande",
        "Cuiabá",
        "Curitiba",
        "Florianópolis",
        "Fortaleza",
        "Goiânia",
        "João Pessoa",
        "Macapá",
        "Maceió",
        "Manaus",
        "Natal",
        "Palmas",
        "Porto Alegre",
        "Porto Velho",
        "Recife",
        "Rio Branco",
        "Rio de Janeiro",
        "Salvador",
        "São Luís",
        "São Paulo",
        "Teresina",
        "Vitória",
    )

    private val viewModel: SearchWeatherForecastViewModel by viewModels()
    private val serviceApi by lazy { services() }

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationManager: LocationManager
    private lateinit var temperature: TextView
    private lateinit var indexUv: TextView
    private lateinit var relativeHumidity: TextView
    private lateinit var precipitation: TextView
    private lateinit var isDay: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var hourlyAdapter: HourlyAdapter

    private val locationPermissionCode = 2
    private var hourlyList: ArrayList<Double> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        temperature = binding.temperature
        indexUv = binding.indexUv
        relativeHumidity = binding.relativeHumidity
        precipitation = binding.precipitation
        isDay = binding.isDay
        recyclerView = binding.recyclerHourly

        hourlyAdapter = HourlyAdapter(hourlyList)

        val llm = LinearLayoutManager(this)
        recyclerView.layoutManager = llm
        recyclerView.adapter = hourlyAdapter
        binding.recyclerHourly.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, brazilianCapitals)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_left_item)

        with(binding.spinnerBrazilianCapitals) {
            adapter = arrayAdapter
            onItemSelectedListener = this@MainActivity
        }

        getPermissionLocation()
    }

    private fun getDataHourly(hourlyModel: HourlyModel) {

        hourlyList.clear()
        hourlyList.addAll(hourlyModel.temperature_2m)

        hourlyAdapter.notifyDataSetChanged()
    }

    private fun getResponse(latitude: Double, longitude: Double) {
        serviceApi.getWeatherForecast(latitude, longitude)
            .enqueue(object : Callback<WeatherForecastModel> {
                override fun onResponse(
                    call: Call<WeatherForecastModel>,
                    response: Response<WeatherForecastModel>
                ) {
                    if (response.isSuccessful) {
                        val weatherForecastModel = response.body()
                        temperature.text =
                            weatherForecastModel?.hourly?.temperature_2m?.get(0).toString() + "°"
                        weatherForecastModel?.hourly?.let { getDataHourly(it) }

                        indexUv.text = weatherForecastModel?.daily?.uv_index_max?.get(0).toString()
                        relativeHumidity.text =
                            weatherForecastModel?.current?.relative_humidity_2m.toString() + "%"
                        precipitation.text =
                            weatherForecastModel?.daily?.precipitation_probability_max?.get(0)
                                .toString()
                        isDay.text = weatherForecastModel?.current?.is_day.toString()
                        if(isDay.equals(1)) {
                            isDay.setText("dia")
                        } else {
                            isDay.setText("noite")
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
        getResponse(location.latitude, location.longitude)
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
        when (view?.id) {
            1 -> showToast(message = "Previsão do tempo em ${brazilianCapitals[position]}")
            else -> {
                showToast(message = "Previsão do tempo em ${brazilianCapitals[position]}")
            }
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