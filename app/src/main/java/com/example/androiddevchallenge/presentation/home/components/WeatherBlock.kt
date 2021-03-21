package com.example.androiddevchallenge.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.components.Spacers
import com.example.androiddevchallenge.domain.entities.Place
import com.example.androiddevchallenge.domain.entities.WeatherSnapshot
import com.example.androiddevchallenge.presentation.home.extensions.displayText
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.roundToInt

@Composable
internal fun WeatherBlock(
    place: Place,
    weather: WeatherSnapshot,
    onChangePlace: () -> Unit
) {
    var showAsCelsius by rememberSaveable { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {
        AnimatedBackdrop(weather)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(dimensionResource(R.dimen.spacing_l))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(onClick = onChangePlace)
                    .padding(dimensionResource(R.dimen.spacing_xs))
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationCity,
                    contentDescription = null,
                )
                Spacers.Xs()

                Text(
                    text = place.displayText(),
                    style = MaterialTheme.typography.h5,
                )
            }

            val temperatureValue = if (showAsCelsius) {
                weather.temperature.asCelsius()
            } else {
                weather.temperature.asFahrenheit()
            }
            Text(
                text = stringResource(R.string.common_temperature, temperatureValue.roundToInt()),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Box(
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.spacing_xs))
                .padding(dimensionResource(R.dimen.spacing_l))
                .statusBarsPadding()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondary.copy(alpha = 0.3f),
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .clickable { showAsCelsius = !showAsCelsius }
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = if (showAsCelsius) {
                    stringResource(R.string.common_fahrenheit_sign)
                } else {
                    stringResource(R.string.common_celsius_sign)
                },
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.spacing_s),
                        vertical = dimensionResource(R.dimen.spacing_xs)
                    )
            )
        }
    }
}
