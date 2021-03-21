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
package com.example.androiddevchallenge.presentation.home

import com.example.androiddevchallenge.core.mvi.MviComposeViewModel
import com.example.androiddevchallenge.di.AppModule
import com.example.androiddevchallenge.domain.services.WeatherService
import com.example.androiddevchallenge.presentation.home.data.WeatherData

class HomeViewModel(
    private val weatherService: WeatherService = AppModule.provideWeatherService()
) : MviComposeViewModel<HomeViewState, HomeCommand>(HomeViewState()) {

    override fun onCommand(command: HomeCommand) {
        when (command) {
            is HomeCommand.LoadData -> {
                executeCatching({
                    WeatherData(
                        current = weatherService.getCurrent(command.place),
                        week = weatherService.getWeekForecast(command.place)
                    )
                }) {
                    copy(currentPlace = command.place, weatherData = it)
                }
            }
        }
    }
}
