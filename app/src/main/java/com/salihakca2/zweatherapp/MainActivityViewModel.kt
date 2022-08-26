package com.salihakca2.zweatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salihakca2.zweatherapp.model.WeatherModel
import com.salihakca2.zweatherapp.service.WeatherApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivityViewModel: ViewModel() {

    private val weatherAPIService = WeatherApiService()
    private val disposable = CompositeDisposable()

    //val weather_data = MutableLiveData<WeatherModel>()
    val weather_data = MutableLiveData<WeatherModel>()

    fun refreshData(cityName: String){
        getDataFromAPI(cityName)
    }


    private fun getDataFromAPI(cityName: String) {

        disposable.add(
            weatherAPIService.getDataService(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                    weather_data.value = t

                    }
                    override fun onError(e: Throwable) {
                    }
                })
        )

    }

}