package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.ammaryasser.masb7ty.R


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7ScreenTopBar(
    total: Int,
    onAddClicked: () -> Unit,
    onUnifyAllClicked: () -> Unit,
    onClearAllClicked: () -> Unit,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit,
) {

    TopBar(
        stringResource(R.string.tasabee7) + " ($total)",
        true,
        onNavBack
    ) {

        var isMenuExpanded by remember { mutableStateOf(false) }

        IconButton(onClick = onAddClicked) {
            Icon(
                imageVector = Outlined.Add,
                contentDescription = stringResource(R.string.add_tasbee7),
                tint = colorScheme.onPrimary
            )
        }

        IconButton(onClick = { isMenuExpanded = true }) {
            Icon(
                imageVector = Outlined.MoreVert,
                contentDescription = stringResource(R.string.more),
                tint = colorScheme.onPrimary
            )
        }


        Tasabee7TopBarMenu(
            isMenuExpanded = isMenuExpanded,
            onMenuDismiss = {
                isMenuExpanded = false
            },
            onItemClearAllCounters = onClearAllClicked,
            onItemUnifyAllTargets = onUnifyAllClicked,
            onItemAbout = onNavToAboutScreen,
        )

    }

}
