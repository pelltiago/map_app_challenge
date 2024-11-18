package com.example.uala.data.network.Cities

import com.example.uala.data.model.City
import com.example.uala.data.network.CitiesApiService
import com.example.uala.data.network.RetrofitHelper

class CitiesRepository {
    private val api = RetrofitHelper.getRetrofit().create(CitiesApiService::class.java)

    suspend fun getCities(): List<City> {
        return api.getCities()
    }
}
