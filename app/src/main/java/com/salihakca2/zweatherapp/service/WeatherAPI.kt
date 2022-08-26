package com.salihakca2.zweatherapp.service

import com.salihakca2.zweatherapp.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //@GET("https://api.openweathermap.org/data/2.5/weather?q=ankara&appid=ee64d4a0e5cf78c6f51f74bf0db2db75&lang=tr")
/*
    @GET("data/2.5/weather?q=ankara&appid=ee64d4a0e5cf78c6f51f74bf0db2db75&lang=tr")
    fun getData(): Single<WeatherModel>

 */

    @GET("data/2.5/weather?&appid=ee64d4a0e5cf78c6f51f74bf0db2db75&lang=tr&units=metric")
    fun getData(
        @Query("q") cityName: String): Single<WeatherModel>

}