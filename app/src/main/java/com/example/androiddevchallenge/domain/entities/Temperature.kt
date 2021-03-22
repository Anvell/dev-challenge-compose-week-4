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
package com.example.androiddevchallenge.domain.entities

sealed class Temperature {

    class Fahrenheit(val value: Float) : Temperature()

    class Celsius(val value: Float) : Temperature()

    fun asCelsius(): Float = when (this) {
        is Celsius -> value
        is Fahrenheit -> value.toCelsius()
    }

    fun asFahrenheit(): Float = when (this) {
        is Celsius -> value.toFahrenheit()
        is Fahrenheit -> value
    }

    fun getValue(unit: TemperatureUnit) = when (unit) {
        TemperatureUnit.Celsius -> asCelsius()
        TemperatureUnit.Fahrenheit -> asFahrenheit()
    }

    private fun Float.toCelsius() = (this - 32) / 1.8f

    private fun Float.toFahrenheit() = this * 1.8f + 32
}
