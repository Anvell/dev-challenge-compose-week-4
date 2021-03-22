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
package com.example.androiddevchallenge.presentation.home.components.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.components.Spacers
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.home.extensions.displayText
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun WeatherDetailsBlock(
    title: String,
    weather: WeatherSnapshot,
    temperatureUnit: TemperatureUnit,
    isHeader: Boolean = false,
    isExpanded: Boolean = false
) {
    var expanded by rememberSaveable { mutableStateOf(isExpanded) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                when {
                    isHeader -> Modifier
                    else -> Modifier.clickable { expanded = !expanded }
                }
            )
            .padding(horizontal = dimensionResource(R.dimen.spacing_m))
            .padding(dimensionResource(R.dimen.spacing_xs))
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(1f)
        )
        Spacers.M()

        if (!isHeader) {
            Spacers.S()
            Text(
                text = stringResource(
                    R.string.common_temperature,
                    weather.temperature.getValue(temperatureUnit).roundToInt()
                ),
                style = MaterialTheme.typography.h6
            )
        }
    }

    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(),
        exit = shrinkVertically(),
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_m))
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.spacing_xs))
            ) {
                WeatherConditionIcon(weather.condition)
                Spacers.Xs()

                Text(
                    text = weather.condition.displayText(),
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.spacing_xs))
            ) {
                PropertyBlock(
                    title = stringResource(R.string.weather_properties_humidity, weather.humidity),
                    description = stringResource(R.string.weather_properties_humidity_description)
                )
                Spacers.S()

                PropertyBlock(
                    title = stringResource(
                        R.string.weather_properties_precipitation,
                        weather.precipitation
                    ),
                    description = stringResource(R.string.weather_properties_precipitation_description)
                )
                Spacers.S()

                PropertyBlock(
                    title = stringResource(R.string.weather_properties_pressure, weather.pressure),
                    description = stringResource(R.string.weather_properties_pressure_description)
                )
                Spacers.S()

                PropertyBlock(
                    title = stringResource(R.string.weather_properties_wind, weather.windSpeed),
                    description = stringResource(R.string.weather_properties_wind_description)
                )
            }
        }
    }
}
