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
@file:Suppress("unused")

package com.example.androiddevchallenge.core.components

import androidx.compose.material.icons.materialIcon
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.DefaultFillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import com.example.androiddevchallenge.core.theme.AppTheme

private var sunIcon: ImageVector? = null
private var cloudIcon: ImageVector? = null

val AppTheme.Icons.Sun: ImageVector
    get() = sunIcon ?: materialIcon(name = "Sun") {
        path(
            fill = Brush.verticalGradient(
                0f to Color(0xFFFFAD83),
                0.3f to Color(0xFFFF57A5)
            ),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = DefaultFillType,
        ) {
            moveTo(6.76f, 4.84f)
            lineToRelative(-1.8f, -1.79f)
            lineToRelative(-1.41f, 1.41f)
            lineToRelative(1.79f, 1.79f)
            close()
            moveTo(1.0f, 10.5f)
            horizontalLineToRelative(3.0f)
            verticalLineToRelative(2.0f)
            lineTo(1.0f, 12.5f)
            close()
            moveTo(11.0f, 0.55f)
            horizontalLineToRelative(2.0f)
            lineTo(13.0f, 3.5f)
            horizontalLineToRelative(-2.0f)
            close()
            moveTo(19.04f, 3.045f)
            lineToRelative(1.408f, 1.407f)
            lineToRelative(-1.79f, 1.79f)
            lineToRelative(-1.407f, -1.408f)
            close()
            moveTo(17.24f, 18.16f)
            lineToRelative(1.79f, 1.8f)
            lineToRelative(1.41f, -1.41f)
            lineToRelative(-1.8f, -1.79f)
            close()
            moveTo(20.0f, 10.5f)
            horizontalLineToRelative(3.0f)
            verticalLineToRelative(2.0f)
            horizontalLineToRelative(-3.0f)
            close()
            moveTo(12.0f, 5.5f)
            curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f)
            reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f)
            reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f)
            reflectiveCurveToRelative(-2.69f, -6.0f, -6.0f, -6.0f)
            close()
            moveTo(12.0f, 15.5f)
            curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f)
            reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f)
            reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f)
            reflectiveCurveToRelative(-1.79f, 4.0f, -4.0f, 4.0f)
            close()
            moveTo(11.0f, 19.5f)
            horizontalLineToRelative(2.0f)
            verticalLineToRelative(2.95f)
            horizontalLineToRelative(-2.0f)
            close()
            moveTo(3.55f, 18.54f)
            lineToRelative(1.41f, 1.41f)
            lineToRelative(1.79f, -1.8f)
            lineToRelative(-1.41f, -1.41f)
            close()
        }
    }.also { sunIcon = it }

val AppTheme.Icons.Cloud: ImageVector
    get() = cloudIcon ?: materialIcon(name = "Cloud") {
        path(
            fill = Brush.verticalGradient(
                0f to Color(0xFF2EADFE),
                0.3f to Color(0xFFAA57D4)
            ),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Bevel,
            strokeLineMiter = 1f,
            pathFillType = DefaultFillType,
        ) {
            moveTo(12.0f, 6.0f)
            curveToRelative(2.62f, 0.0f, 4.88f, 1.86f, 5.39f, 4.43f)
            lineToRelative(0.3f, 1.5f)
            lineToRelative(1.53f, 0.11f)
            curveToRelative(1.56f, 0.1f, 2.78f, 1.41f, 2.78f, 2.96f)
            curveToRelative(0.0f, 1.65f, -1.35f, 3.0f, -3.0f, 3.0f)
            horizontalLineTo(6.0f)
            curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f)
            curveToRelative(0.0f, -2.05f, 1.53f, -3.76f, 3.56f, -3.97f)
            lineToRelative(1.07f, -0.11f)
            lineToRelative(0.5f, -0.95f)
            curveTo(8.08f, 7.14f, 9.94f, 6.0f, 12.0f, 6.0f)
            moveToRelative(0.0f, -2.0f)
            curveTo(9.11f, 4.0f, 6.6f, 5.64f, 5.35f, 8.04f)
            curveTo(2.34f, 8.36f, 0.0f, 10.91f, 0.0f, 14.0f)
            curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f)
            horizontalLineToRelative(13.0f)
            curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f)
            curveToRelative(0.0f, -2.64f, -2.05f, -4.78f, -4.65f, -4.96f)
            curveTo(18.67f, 6.59f, 15.64f, 4.0f, 12.0f, 4.0f)
            close()
        }
    }.also { cloudIcon = it }
