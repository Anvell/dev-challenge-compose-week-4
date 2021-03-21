/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data.services

import com.example.androiddevchallenge.domain.entities.Condition
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.domain.entities.Temperature
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.domain.services.WeatherService
import java.time.ZonedDateTime
import kotlin.random.Random

class WeatherServiceImpl : WeatherService {
    private val rnd = Random(System.currentTimeMillis())
    private var current: WeatherSnapshot? = null
    private var weekForecast: List<WeatherSnapshot>? = null

    override fun getCurrent(place: Place): WeatherSnapshot {
        return current ?: createRandomSnapshot(place).also {
            current = it
        }
    }

    override fun getWeekForecast(place: Place): List<WeatherSnapshot> {
        return weekForecast ?: (listOf(getCurrent(place)) + (0..6).map {
            createRandomSnapshot(place)
        }).also {
            weekForecast = it
        }
    }

    private fun createRandomSnapshot(place: Place) = WeatherSnapshot(
        dateTime = ZonedDateTime.now(),
        condition = place.getCondition(),
        temperature = Temperature.Celsius(rnd.nextInt(-5, 33).toFloat()),
        precipitation = rnd.nextInt(0, 3).toFloat(),
        humidity = rnd.nextInt(54, 90),
        pressure = rnd.nextInt(720, 1054),
        windSpeed = rnd.nextInt(0, 22),
    )

    private fun Place.getCondition() = when (this) {
        Place.NewYork -> Condition.Sunny
        Place.London -> Condition.Fog
        Place.Tokyo -> Condition.Clear
    }
}
