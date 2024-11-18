package com.example.uala.domain

import com.example.uala.data.network.Cities.CitiesRepository

class GetCitiesUseCase {
    private val repository = CitiesRepository()
    suspend operator fun invoke() = repository.getCities()
}