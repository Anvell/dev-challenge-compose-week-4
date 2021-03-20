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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviComposeActivity<V, S, C> :
    AppCompatActivity() where V : MviComposeViewModel<S, C> {
    private val pendingCommands = MutableSharedFlow<C>()

    abstract val viewModel: V

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            pendingCommands.collect { onCommand(it) }
        }

        setContent {
            viewModel.stateAsFlow()
                .collectAsState()
                .value
                ?.let { state ->
                    ProvideWindowInsets {
                        Сontent(
                            state = state,
                            commands = {
                                lifecycleScope.launch { pendingCommands.emit(it) }
                            }
                        )
                    }
                }
        }
    }

    protected open fun onCommand(command: C) = viewModel.emitCommand(command)

    @Composable
    abstract fun Сontent(state: S, commands: (C) -> Unit)
}
