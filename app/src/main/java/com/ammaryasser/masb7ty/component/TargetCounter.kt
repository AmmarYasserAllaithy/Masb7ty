package com.ammaryasser.masb7ty.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.R


@Composable
fun TargetCounter(
    count: Int,
    target: Int,
    modifier: Modifier = Modifier,
    color: Color = colorScheme.onSurface.copy(.5f),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "${stringResource(R.string.count)}: $count",
            modifier = Modifier.weight(1f),
            color = color
        )

        Text(
            text = "${stringResource(R.string.target)}: $target",
            modifier = Modifier.weight(1f),
            color = color
        )
    }
}
