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

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.domain.entities.Condition
import com.example.androiddevchallenge.domain.entities.Temperature
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.launcher.LauncherActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime

private const val TestTitle = "TestTitle"
private val TestSnapshot = WeatherSnapshot(
    dateTime = ZonedDateTime.now(),
    condition = Condition.Clear,
    temperature = Temperature.Celsius(42f),
    precipitation = 0f,
    humidity = 50,
    pressure = 1000,
    windSpeed = 0,
)

class WeatherDetailsBlockTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            AppTheme {
                WeatherDetailsBlock(
                    title = TestTitle,
                    weather = TestSnapshot,
                    temperatureUnit = TemperatureUnit.Celsius,
                    isHeader = false,
                    isExpanded = false
                )
            }
        }
    }

    @Test
    fun canBeExpanded(): Unit = with(composeTestRule.activity) {
        with(composeTestRule) {
            onNodeWithText(
                getString(R.string.weather_properties_humidity_description),
                useUnmergedTree = true
            ).assertDoesNotExist()
            onNodeWithText(TestTitle, useUnmergedTree = true).onParent()
                .performClick()
            onNodeWithText(
                text = getString(R.string.weather_properties_humidity_description),
                useUnmergedTree = true
            ).assertExists()
        }
    }
}
