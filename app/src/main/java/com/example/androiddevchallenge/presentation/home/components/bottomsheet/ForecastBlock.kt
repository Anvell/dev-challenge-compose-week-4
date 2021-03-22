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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.components.Spacers
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.presentation.home.data.WeatherData
import com.example.androiddevchallenge.presentation.home.extensions.getName
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
internal fun ForecastBlock(
    weather: WeatherData,
    temperatureUnit: TemperatureUnit
) {
    require(weather.week.size == 7) { "Weekly forecast is expected." }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(vertical = dimensionResource(R.dimen.spacing_m))
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHandle(
            Modifier.align(Alignment.CenterHorizontally)
        )
        Spacers.S()

        WeatherDetailsBlock(
            title = stringResource(R.string.common_weekdays_today),
            weather = weather.current,
            temperatureUnit = temperatureUnit,
            isHeader = true,
            isExpanded = true
        )

        weather.week.drop(1).forEachIndexed { i, item ->
            if (i > 0) Divider()
            Spacers.Xs()

            WeatherDetailsBlock(
                title = item.dateTime.dayOfWeek.getName(),
                weather = item,
                temperatureUnit = temperatureUnit
            )
            Spacers.Xs()
        }
    }
}
