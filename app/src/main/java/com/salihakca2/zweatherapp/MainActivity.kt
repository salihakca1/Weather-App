package com.salihakca2.zweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.salihakca2.zweatherapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var dataAdapter: ArrayAdapter<String>
    private val cities= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tempViewModel: MainActivityViewModel by viewModels()
        viewModel = tempViewModel

        cities.add("ADANA")
        cities.add("ADIYAMAN")
        cities.add("AFYONKARAHİSAR")
        cities.add("AĞRI")
        cities.add("AMASYA")
        cities.add("ANKARA")
        cities.add("ANTALYA")
        cities.add("ARTVİN")
        cities.add("AYDIN")
        cities.add("BALIKESİR")
        cities.add("BİLECİK")
        cities.add("BİNGÖL")
        cities.add("BİTLİS")
        cities.add("BOLU")
        cities.add("BURDUR")
        cities.add("BURSA")
        cities.add("ÇANAKKALE")
        cities.add("ÇANKIRI")
        cities.add("ÇORUM")
        cities.add("DENİZLİ")
        cities.add("DİYARBAKIR")
        cities.add("EDİRNE")
        cities.add("ELAZIĞ")
        cities.add("ERZİNCAN")
        cities.add("ERZURUM")
        cities.add("ESKİŞEHİR")
        cities.add("GAZİANTEP")
        cities.add("GİRESUN")
        cities.add("GÜMÜŞHANE")
        cities.add("HAKKARİ")
        cities.add("İSTANBUL")
        cities.add("İZMİR")
        cities.add("KARS")
        cities.add("KASTAMONU")
        cities.add("KAYSERİ")
        cities.add("ORDU")
        cities.add("RİZE")
        cities.add("SİVAS")
        cities.add("AKSARAY")
        cities.add("ŞIRNAK")
        cities.add("DÜZCE")


        dataAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,cities)

        binding.spinner.adapter = dataAdapter

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.refreshData(cities[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        getLiveData()
    }

    private fun getLiveData() {

        viewModel.weather_data.observe(this, Observer { data->
            data?.let {
                binding.textViewDegree.text = data.main.temp.toString()
                binding.textViewCity.text = data.name
                binding.textViewDescription.text = data.weather[0].description
                binding.textViewDate.text = getDate()

                Picasso.get().load("https://openweathermap.org/img/wn/"+ data.weather[0].icon +"@2x.png").into(binding.imageView);
            }
        })
    }

    private fun getDate(): String{
        var takvim= Calendar.getInstance().time
        var formatlayici = SimpleDateFormat("EEEE, MMMM yyyy", Locale("tr"))
        var tarih = formatlayici.format(takvim)
        return tarih
    }

}

//https://api.openweathermap.org/data/2.5/weather?q=ankara&appid=ee64d4a0e5cf78c6f51f74bf0db2db75&lang=tr