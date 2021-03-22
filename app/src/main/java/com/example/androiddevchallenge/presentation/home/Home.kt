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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.core.components.OptionDialog
import com.example.androiddevchallenge.core.components.OptionDialogItem
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.core.theme.Colors
import com.example.androiddevchallenge.domain.datatypes.Loading
import com.example.androiddevchallenge.domain.datatypes.Success
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.presentation.home.components.bottomsheet.ForecastBlock
import com.example.androiddevchallenge.presentation.home.components.top.WeatherBlock
import com.example.androiddevchallenge.presentation.home.extensions.displayText
import com.example.androiddevchallenge.presentation.home.extensions.isNightTime

private val SheetPeekHeight = 200.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    state: HomeViewState,
    commands: (HomeCommand) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var showPlaceSelection by rememberSaveable { mutableStateOf(false) }
    var temperatureUnit by rememberSaveable { mutableStateOf(TemperatureUnit.Celsius) }

    Box(Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetContent = {
                state.weatherData()?.let {
                    ForecastBlock(
                        weather = it,
                        temperatureUnit = temperatureUnit
                    )
                }
            },
            sheetPeekHeight = with(LocalDensity.current) { SheetPeekHeight.toDp() },
        ) {
            when (state.weatherData) {
                is Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Success -> {
                    val weather = state.weatherData.unwrap().current

                    CompositionLocalProvider(
                        LocalContentColor provides if (weather.dateTime.isNightTime()) {
                            Colors.Alabaster
                        } else {
                            Colors.Mirage
                        }
                    ) {
                        WeatherBlock(
                            place = state.currentPlace,
                            weather = weather,
                            temperatureUnit = temperatureUnit,
                            onChangePlace = { showPlaceSelection = true },
                            onChangeTemperatureUnit = { temperatureUnit = it }
                        )
                    }
                }
                else -> Unit
            }
        }

        if (showPlaceSelection) {
            AppTheme.OptionDialog(
                items = Place.values().map { place ->
                    OptionDialogItem(
                        title = place.displayText(),
                        action = {
                            commands(HomeCommand.LoadData(place))
                            showPlaceSelection = false
                        }
                    )
                },
                onDismissRequest = {
                    showPlaceSelection = false
                }
            )
        }
    }

    LaunchedEffect(state.currentPlace) {
        commands(HomeCommand.LoadData(state.currentPlace))
    }
}
