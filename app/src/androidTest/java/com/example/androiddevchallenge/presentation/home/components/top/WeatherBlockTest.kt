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

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.locals.LocalSystemUiController
import com.example.androiddevchallenge.core.locals.SystemUiController
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.domain.entities.Condition
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.domain.entities.Temperature
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.launcher.LauncherActivity
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime
import kotlin.math.roundToInt

private const val TestTemperature = 42f
private val TestSnapshot = WeatherSnapshot(
    dateTime = ZonedDateTime.now(),
    condition = Condition.Clear,
    temperature = Temperature.Celsius(TestTemperature),
    precipitation = 0f,
    humidity = 50,
    pressure = 1000,
    windSpeed = 0,
)

class WeatherBlockTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalSystemUiController provides remember {
                    SystemUiController(composeTestRule.activity.window)
                }
            ) {
                ProvideWindowInsets {
                    AppTheme {
                        WeatherBlock(
                            place = Place.Sydney,
                            weather = TestSnapshot,
                            temperatureUnit = TemperatureUnit.Celsius,
                            onChangePlace = {},
                            onChangeTemperatureUnit = {}
                        )
                    }
                }
            }
        }
    }

    @Test
    fun showsProperTemperature() {
        val temperatureText = composeTestRule.activity
            .getString(R.string.common_temperature, TestTemperature.roundToInt())
        composeTestRule.onNodeWithText(temperatureText).assertIsDisplayed()
    }

    @Test
    fun showsProperCity() {
        val city = composeTestRule.activity.getString(R.string.places_sydney)
        composeTestRule.onNodeWithText(city).assertIsDisplayed()
    }
}
