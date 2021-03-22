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

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.domain.entities.TemperatureUnit
import com.example.androiddevchallenge.presentation.launcher.LauncherActivity
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test

private const val ButtonTag = "TemperatureUnitButton"

class TemperatureUnitButtonTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Test
    fun clickingChangesTemperatureUnit() {
        var temperatureUnit = TemperatureUnit.Celsius

        composeTestRule.setContent {
            AppTheme {
                TemperatureUnitButton(
                    current = temperatureUnit,
                    onChanged = { temperatureUnit = it },
                    modifier = Modifier.testTag(ButtonTag)
                )
            }
        }
        composeTestRule.onNodeWithTag(ButtonTag).performClick()

        temperatureUnit shouldBe TemperatureUnit.Fahrenheit
    }
}
