package com.example.androiddevchallenge.presentation.home.data

import com.example.androiddevchallenge.domain.entities.WeatherSnapshot

data class WeatherData(
    val current: WeatherSnapshot,
    val week: List<WeatherSnapshot>
)
