package com.ammaryasser.masb7ty.component

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.R


@Composable
fun Tasabee7TopBarMenu(
    isMenuExpanded: Boolean,
    onMenuDismiss: () -> Unit,
    onItemClearAllCounters: () -> Unit,
    onItemUnifyAllTargets: () -> Unit,
    onItemAbout: () -> Unit,
) {
    DropdownMenu(
        expanded = isMenuExpanded,
        onDismissRequest = onMenuDismiss
    ) {

        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.clear_all_counters))
            },
            leadingIcon = {
                Icon(Outlined.Refresh, "")
            },
            onClick = {
                onMenuDismiss()
                onItemClearAllCounters()
            }
        )

        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.unify_all_targets))
            },
            leadingIcon = {
                Icon(Outlined.Create, contentDescription = "")
            },
            onClick = {
                onMenuDismiss()
                onItemUnifyAllTargets()
            }
        )

        HorizontalDivider(Modifier.height(1.dp))

        DropdownMenuItem(
            text = {
                Text(stringResource(R.string.about))
            },
            leadingIcon = {
                Icon(Outlined.Info, "")
            },
            onClick = {
                onMenuDismiss()
                onItemAbout()
            }
        )

    }
}
