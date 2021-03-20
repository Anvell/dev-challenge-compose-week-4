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
package com.example.androiddevchallenge.core.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviComposeViewModel<S, C>(initialState: S) : MviViewModel<S>(initialState) {
    private val pendingCommands = MutableSharedFlow<C>()

    init {
        viewModelScope.launch {
            pendingCommands.collect { onCommand(it) }
        }
    }

    abstract fun onCommand(command: C)

    fun emitCommand(command: C) {
        viewModelScope.launch {
            pendingCommands.emit(command)
        }
    }
}
