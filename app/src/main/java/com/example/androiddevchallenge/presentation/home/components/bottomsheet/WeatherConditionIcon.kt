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

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.core.components.Cloud
import com.example.androiddevchallenge.core.components.Sun
import com.example.androiddevchallenge.core.theme.AppTheme
import com.example.androiddevchallenge.domain.entities.Condition
import com.example.androiddevchallenge.presentation.home.extensions.displayText

@Composable
internal fun WeatherConditionIcon(
    condition: Condition,
    modifier: Modifier = Modifier
) {
    val icon = when (condition) {
        Condition.Clear -> AppTheme.Icons.Sun
        Condition.Sunny -> AppTheme.Icons.Sun
        Condition.Fog -> AppTheme.Icons.Cloud
    }

    Image(
        imageVector = icon,
        contentDescription = condition.displayText(),
        modifier = modifier
    )
}
