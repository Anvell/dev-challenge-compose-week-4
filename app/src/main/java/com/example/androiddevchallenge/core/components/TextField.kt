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
package com.example.androiddevchallenge.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.core.theme.AppTheme

private val TextFieldHeight = 56.dp
private val TextFieldUnderlineWidth = 1.dp
private val TextFieldHorizontalPadding = 24.dp
private val TextFieldLeadingPadding = PaddingValues(start = 16.dp, end = 8.dp)

private val textSelectionColors
    @Composable get() = TextSelectionColors(
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.4f)
    )

@Composable
fun AppTheme.TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextFieldFocused: (Boolean) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textColor: Color = MaterialTheme.colors.onSurface,
    cursorColor: Color = MaterialTheme.colors.secondary,
    backgroundColor: Color = MaterialTheme.colors.surface,
    shape: Shape = MaterialTheme.shapes.small.copy(
        bottomStart = ZeroCornerSize,
        bottomEnd = ZeroCornerSize
    ),
    horizontalPadding: Dp = TextFieldHorizontalPadding,
    singleLine: Boolean = true
) {
    var lastFocusState by remember { mutableStateOf(FocusState.Inactive) }
    val color = textColor.takeOrElse {
        textStyle.color.takeOrElse { LocalContentColor.current }
    }
    val mergedStyle = textStyle.merge(TextStyle(color = color))
    val underlineColor = MaterialTheme.colors.primary

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.onFocusEvent { state ->
            if (lastFocusState != state) {
                onTextFieldFocused(state == FocusState.Active)
            }
            lastFocusState = state
        },
        textStyle = mergedStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(cursorColor)
    ) { innerTextField ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor, shape)
                .height(TextFieldHeight)
                .drawBehind {
                    drawLine(
                        color = underlineColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = TextFieldUnderlineWidth.toPx()
                    )
                }
        ) {
            leading?.let { content ->
                Box(
                    Modifier
                        .padding(TextFieldLeadingPadding)
                        .wrapContentWidth()
                ) {
                    content()
                }
            }

            CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
                Box(
                    Modifier
                        .padding(
                            start = if (leading != null) 0.dp else horizontalPadding,
                            end = horizontalPadding
                        ).weight(1f)
                ) {
                    innerTextField()

                    if (value.isEmpty() &&
                        !lastFocusState.isFocused &&
                        label != null
                    ) {
                        ProvideTextStyle(
                            value = textStyle.copy(color = textColor),
                            content = label
                        )
                    }
                }
            }
        }
    }
}
