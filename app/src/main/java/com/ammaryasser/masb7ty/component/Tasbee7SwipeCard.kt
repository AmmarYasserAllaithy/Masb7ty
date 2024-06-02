package com.ammaryasser.masb7ty.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.util.rocoshape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tasbee7SwipeCard(
    text: String,
    count: Int,
    target: Int,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = rocoshape,
        elevation = CardDefaults.cardElevation(1.dp),
        onClick = onClick,
    ) {

        val dismissState = rememberSwipeToDismissBoxState(
            confirmValueChange = {
                if (it == StartToEnd) {
                    onEdit()
                    true
                } else if (it == EndToStart) {
                    onDelete()
                    true
                }
                false
            }
        )

        SwipeToDismissBox(
            state = dismissState,
            modifier = Modifier.clip(rocoshape),
            backgroundContent = {
                val direction = dismissState.dismissDirection

                val color by animateColorAsState(
                    when (dismissState.targetValue) {
                        EndToStart -> Color.Red.copy(alpha = 0.8f)
                        StartToEnd -> colorScheme.secondary.copy(alpha = 0.8f)
                        else -> colorScheme.background
                    }
                )

                val scale by animateFloatAsState(
                    if (dismissState.targetValue == Settled) 0f
                    else 1.2f
                )

                val icon = when (direction) {
                    StartToEnd -> Outlined.Edit
                    EndToStart -> Outlined.Delete
                    else -> Outlined.Delete
                }

                val alignment = when (direction) {
                    StartToEnd -> Alignment.CenterStart
                    EndToStart -> Alignment.CenterEnd
                    else -> Alignment.Center
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = color)
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        icon, "",
                        Modifier
                            .scale(scale)
                            .align(alignment), Color.White
                    )
                }
            }
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        text,
                        color = if (count >= target) colorScheme.secondary else colorScheme.onSurface
                    )
                },
                supportingContent = { Text("$count ($target)") },
                modifier = Modifier.clip(rocoshape),
            )
        }

    }
}


/*
@Composable
fun SwipeToDismissBox(
    state: SwipeToDismissBoxState,
    backgroundContent: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    enableDismissFromStartToEnd: Boolean = true,
    enableDismissFromEndToStart: Boolean = true,
    gesturesEnabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
): Unit
*/