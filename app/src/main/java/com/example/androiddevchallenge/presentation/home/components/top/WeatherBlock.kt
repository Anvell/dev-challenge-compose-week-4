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
package com.example.androiddevchallenge.presentation.home.components.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.components.Spacers
import com.example.androiddevchallenge.core.locals.LocalSystemUiController
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.home.extensions.displayText
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.roundToInt

@Composable
internal fun WeatherBlock(
    place: Place,
    weather: WeatherSnapshot,
    temperatureUnit: TemperatureUnit,
    onChangePlace: () -> Unit,
    onChangeTemperatureUnit: (TemperatureUnit) -> Unit
) {
    val systemUiController = LocalSystemUiController.current
    val contentColor = LocalContentColor.current

    Box(Modifier.fillMaxSize()) {
        AnimatedBackdrop(weather)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(dimensionResource(R.dimen.spacing_l))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(
                        onClickLabel = stringResource(R.string.change_place_button_label),
                        role = Role.Button,
                        onClick = onChangePlace
                    )
                    .padding(dimensionResource(R.dimen.spacing_xs))
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationCity,
                    contentDescription = null,
                )
                Spacers.Xs()

                Text(
                    text = place.displayText(),
                    style = MaterialTheme.typography.h5,
                )
            }

            Text(
                text = stringResource(
                    R.string.common_temperature,
                    weather.temperature.getValue(temperatureUnit).roundToInt()
                ),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }

        TemperatureUnitButton(
            current = temperatureUnit,
            onChanged = onChangeTemperatureUnit,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.spacing_xs))
                .padding(dimensionResource(R.dimen.spacing_l))
                .statusBarsPadding()
                .align(Alignment.TopEnd)
        )
    }

    LaunchedEffect(contentColor) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = contentColor.luminance() < 0.5f
        )
    }
}
