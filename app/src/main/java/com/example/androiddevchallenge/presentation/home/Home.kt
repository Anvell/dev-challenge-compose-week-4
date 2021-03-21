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
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.core.components.OptionDialog
import com.example.androiddevchallenge.core.components.OptionDialogItem
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.domain.datatypes.Loading
import com.example.androiddevchallenge.domain.datatypes.Success
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.presentation.home.components.WeatherBlock
import com.example.androiddevchallenge.presentation.home.extensions.displayText
import com.example.androiddevchallenge.presentation.home.extensions.isNightTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    state: HomeViewState,
    commands: (HomeCommand) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var showPlaceSelection by rememberSaveable { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetContent = {
            },
            sheetPeekHeight = 156.dp,
        ) {
            when (state.weatherData) {
                is Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Success -> {
                    val weather = state.weatherData.unwrap().current
                    AppTheme(weather.dateTime.isNightTime()) {
                        WeatherBlock(
                            place = state.currentPlace,
                            weather = weather,
                            onChangePlace = { showPlaceSelection = true }
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

