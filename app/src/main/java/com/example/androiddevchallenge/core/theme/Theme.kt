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
package com.example.androiddevchallenge.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorPalette = with(Colors) {
    lightColors(
        primary = Orange,
        secondary = Orange,
        background = Alabaster,
        surface = White,
        onPrimary = Mirage,
        onSecondary = White,
        onBackground = Mirage,
        onSurface = Mirage
    )
}

private val DarkColorPalette = with(Colors) {
    darkColors(
        primary = White,
        secondary = Orange,
        background = Mirage,
        surface = BigStone,
        onPrimary = Mirage,
        onSecondary = White,
        onBackground = Alabaster,
        onSurface = White
    )
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColorFor(MaterialTheme.colors.background)
        ) {
            content()
        }
    }
}
