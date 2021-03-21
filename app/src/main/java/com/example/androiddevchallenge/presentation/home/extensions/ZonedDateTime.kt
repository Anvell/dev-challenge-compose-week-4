package com.example.androiddevchallenge.presentation.home.extensions

import java.time.ZonedDateTime

internal fun ZonedDateTime.isNightTime() = hour < 4 || hour > 20
