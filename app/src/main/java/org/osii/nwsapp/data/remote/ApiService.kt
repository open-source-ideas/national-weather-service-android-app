package org.osii.nwsapp.data.remote

import io.reactivex.Observable
import io.reactivex.Single
import org.osii.nwsapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val URL = "https://api.weather.gov/"
        val TEST_LATITUDE = 39.7456
        val TEST_LONGITUDE = -97.0892
    }

    @GET("points/{latitude},{longitude}")
    fun getWeather(@Path("latitude") latitude: Double,
                   @Path("longitude") longitude: Double
    ) : Single<WeatherResponse>

}