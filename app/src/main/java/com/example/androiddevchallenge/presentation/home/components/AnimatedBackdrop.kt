package com.example.androiddevchallenge.presentation.home.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.entities.Condition
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.home.extensions.isNightTime

private const val AnimationDuration = 3000
private const val CrossfadeDuration = 600

@Composable
internal fun AnimatedBackdrop(weather: WeatherSnapshot) {
    val (base, light) = getBackdropImages(weather)
    var lightAlpha by remember { mutableStateOf(0f) }

    Crossfade(
        targetState = weather.condition,
        animationSpec = tween(
            durationMillis = CrossfadeDuration,
            easing = LinearEasing
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(base),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(light),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                alpha = lightAlpha,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(AnimationDuration),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            lightAlpha = value
        }
    }
}

@Composable
private fun getBackdropImages(weather: WeatherSnapshot) = when (weather.condition) {
    Condition.Clear -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_base to R.drawable.night_volume_light
        } else {
            R.drawable.day_base to R.drawable.day_volume_light
        }
    }
    Condition.Sunny -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_base to R.drawable.night_volume_light
        } else {
            R.drawable.day_base to R.drawable.day_volume_light
        }
    }
    Condition.Fog -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_fog_base to R.drawable.night_fog_volume_light
        } else {
            R.drawable.day_fog_base to R.drawable.day_fog_base_volume_light
        }
    }
}
