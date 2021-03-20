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
package com.example.androiddevchallenge.domain.datatypes

import java.util.concurrent.atomic.AtomicBoolean

sealed class Async<out T> {
    open operator fun invoke(): T? = null

    fun unwrap(): T = requireNotNull(invoke())
}

object Uninitialized : Async<Nothing>()

object Loading : Async<Nothing>()

data class Success<out T>(
    val value: T,
    val metadata: Any? = null
) : Async<T>() {
    private val consumed = AtomicBoolean(false)
    val isConsumed
        get() = consumed.get()

    override operator fun invoke(): T {
        consumed.set(true)
        return value
    }

    fun peek() = value
}

data class Fail<out T>(
    private val throwable: Throwable,
    val metadata: Any? = null
) : Async<T>() {
    private val consumed = AtomicBoolean(false)
    val isConsumed
        get() = consumed.get()

    val error: Throwable
        get() {
            consumed.set(true)
            return throwable
        }

    fun peekError() = throwable
}
