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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.domain.datatypes.Async
import com.example.androiddevchallenge.domain.datatypes.Fail
import com.example.androiddevchallenge.domain.datatypes.Loading
import com.example.androiddevchallenge.domain.datatypes.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviViewModel<S>(initialState: S) : ViewModel() {
    private val state = MutableStateFlow(initialState)

    fun stateAsFlow(): StateFlow<S> = state

    fun <T> withState(block: (S) -> T) = block(state.value)

    protected fun setState(reducer: S.() -> S) {
        state.value = reducer(state.value)
    }

    protected suspend fun <V> Flow<V>.executeCatching(reducer: S.(Async<V>) -> S) {
        catch { setState { reducer(Fail(it)) } }
        collect { setState { reducer(Success(it)) } }
    }

    protected fun <V> executeCatching(
        block: suspend S.() -> V,
        reducer: S.(Async<V>) -> S
    ) {
        viewModelScope.launch {
            setState { reducer(Loading) }

            try {
                val result = block(state.value)
                setState { reducer(Success(result)) }
            } catch (error: Throwable) {
                setState { reducer(Fail(error)) }
            }
        }
    }
}
