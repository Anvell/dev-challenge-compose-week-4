package com.example.androiddevchallenge.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.core.theme.AppTheme

data class OptionDialogItem(
    val title: String,
    val action: () -> Unit,
)

@Composable
fun AppTheme.OptionDialog(
    items: List<OptionDialogItem>,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppTheme.Dialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Column {
                items.forEachIndexed { i, item ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onDismissRequest()
                                item.action()
                            }
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.spacing_s))
                    ) {
                        Text(item.title, color = MaterialTheme.colors.onSurface)
                    }
                    if (i < items.lastIndex) Divider()
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun AppTheme.Dialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            color = backgroundColor,
            contentColor = contentColor,
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                if (title != null) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        val textStyle = MaterialTheme.typography.subtitle1.copy(
                            textAlign = TextAlign.Center
                        )
                        ProvideTextStyle(textStyle, title)
                    }
                    Spacers.M()
                }

                if (text != null) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        val textStyle = MaterialTheme.typography.body1.copy(
                            textAlign = TextAlign.Center
                        )
                        ProvideTextStyle(textStyle, text)
                    }
                    Spacer(Modifier.height(28.dp))
                }
                buttons()
            }
        }
    }
}
