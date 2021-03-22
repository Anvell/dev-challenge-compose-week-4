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

import com.example.androiddevchallenge.di.AppModule
import com.example.androiddevchallenge.domain.entities.Place
import io.kotest.matchers.floats.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.Test

class WeatherServiceImplTest {
    private val weatherService = AppModule.provideWeatherService()

    @Test
    fun `Service returns sensible values`() {
        with(weatherService.getCurrent(Place.NewYork)) {
            humidity shouldBeGreaterThan 0
            pressure shouldBeGreaterThan 0
            precipitation shouldBeGreaterThanOrEqual 0f
            windSpeed shouldBeGreaterThanOrEqual 0
            temperature.asCelsius() should { it > -80 && it < 80 }
        }
    }

    @Test
    fun `Week forecast returns proper number of days`() {
        val items = weatherService.getWeekForecast(Place.NewYork)
        items.size shouldBe 7
    }
}
