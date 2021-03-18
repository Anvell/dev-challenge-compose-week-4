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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.androiddevchallenge.R

object Spacers {
    @Composable
    fun Xs() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xs)))
    }

    @Composable
    fun S() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_s)))
    }

    @Composable
    fun M() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_m)))
    }

    @Composable
    fun L() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_l)))
    }

    @Composable
    fun Xl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xl)))
    }

    @Composable
    fun Xxl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xxl)))
    }

    @Composable
    fun Xxxl() {
        Spacer(Modifier.size(dimensionResource(R.dimen.spacing_xxxl)))
    }
}
