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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7ScreenTopBar(
    total: Int,
    viewModel: Tasabee7ScreenViewModel,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit,
) {
    TopBar(
        stringResource(R.string.tasabee7) + " ($total)",
        true,
        onNavBack
    ) {
        val addDialogShowState = remember { mutableStateOf(false) }
        val unifyDialogShowState = remember { mutableStateOf(false) }
        val menuExpansionState = remember { mutableStateOf(false) }

        IconButton(onClick = { addDialogShowState.value = true }) {
            Icon(Outlined.Add, "", tint = colorScheme.onPrimary)
        }

        IconButton(onClick = { menuExpansionState.value = true }) {
            Icon(Outlined.MoreVert, "", tint = colorScheme.onPrimary)
        }

        AddOrEditTasbee7Dialog(addDialogShowState) { text, target ->
            val save = { txt: String ->
                viewModel.saveTasbee7(Tasbee7(text = txt.trim(), target = target))
            }

            if (text.contains(Regex("\n+")))
                text.split("\n").forEach { save(it) }
            else save(text)
        }

        Tasabee7TopBarMenu(menuExpansionState, unifyDialogShowState, viewModel, onNavToAboutScreen)

        UnifyTargetDialog(unifyDialogShowState, viewModel)
    }
}
