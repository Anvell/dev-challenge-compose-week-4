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
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.random.Random

class WeatherServiceImpl : WeatherService {
    private val rnd = Random(System.currentTimeMillis())
    private val data = Place.values().associate { place ->
        place to (0..6).map { i ->
            createRandomSnapshot(
                dateTime = place.getDateTime().plusDays(i.toLong()),
                condition = if (i == 0) {
                    place.getCondition()
                } else {
                    createRandomCondition()
                }
            )
        }
    }

    override fun getCurrent(place: Place): WeatherSnapshot {
        return data[place]!!.first()
    }

    override fun getWeekForecast(place: Place): List<WeatherSnapshot> {
        return data[place]!!
    }

    private fun createRandomSnapshot(
        dateTime: ZonedDateTime,
        condition: Condition = createRandomCondition()
    ) = WeatherSnapshot(
        dateTime = dateTime,
        condition = condition,
        temperature = Temperature.Celsius(rnd.nextInt(-5, 23).toFloat()),
        precipitation = if (condition != Condition.Clear) rnd.nextFloat() * 4 else 0f,
        humidity = rnd.nextInt(54, 90),
        pressure = rnd.nextInt(980, 1034),
        windSpeed = rnd.nextInt(0, 22),
    )

    private fun Place.getCondition() = when (this) {
        Place.NewYork -> Condition.Sunny
        Place.London -> Condition.Fog
        Place.Tokyo -> Condition.Clear
    }

    private fun createRandomCondition(): Condition {
        return Condition.values()[rnd.nextInt(0, Condition.values().size)]
    }

    private fun Place.getDateTime() = when (this) {
        Place.NewYork -> ZonedDateTime.now(ZoneId.of("America/New_York"))
        Place.London -> ZonedDateTime.now(ZoneId.of("Europe/London"))
        Place.Tokyo -> ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
    }
}
