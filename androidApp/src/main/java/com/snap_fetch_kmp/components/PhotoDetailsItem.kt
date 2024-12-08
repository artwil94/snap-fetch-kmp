package com.snap_fetch_kmp.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.snap_fetch_kmp.android.R
import com.snap_fetch_kmp.theme.SfTheme

@ExperimentalLayoutApi
@Composable
fun PhotoDetailsItem(
    modifier: Modifier = Modifier,
    value: String,
    subtitle: String,
    @DrawableRes icon: Int,
    textStyle: TextStyle = SfTheme.typography.photoDetailSubtitle,
    subtitleTextStyle: TextStyle = SfTheme.typography.photoDetailSubtitle,
    clickableValue: Boolean = false,
    onValueClick: () -> Unit = {}
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = modifier.size(SfTheme.dimensions.photoDetailIconSize),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = SfTheme.colors.actionButton
        )
        Box(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier
                    .padding(start = SfTheme.dimensions.paddingS),
                text = subtitle,
                style = subtitleTextStyle,
                textAlign = TextAlign.Center,
                color = SfTheme.colors.primaryTextGray,
                maxLines = 2,
            )
        }
        Text(
            modifier = Modifier
                .clickable(onClick = { onValueClick.invoke() }, enabled = clickableValue)
                .padding(start = SfTheme.dimensions.paddingL),
            text = value,
            style = textStyle,
            color = Color.White,
            minLines = 1
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
private fun PhotoDetailItemPreview() {
    PhotoDetailsItem(
        value = "Artur Wilczek",
        icon = R.drawable.ic_author,
        textStyle = SfTheme.typography.author,
        subtitle = stringResource(id = R.string.author)
    )
}