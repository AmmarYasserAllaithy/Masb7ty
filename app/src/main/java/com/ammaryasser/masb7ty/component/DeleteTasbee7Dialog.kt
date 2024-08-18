package com.ammaryasser.masb7ty.component

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.screen.tasabee7ViewModel


@Composable
fun DeleteTasbee7Dialog(
    showState: MutableState<Boolean>,
    currentTasbee7: Tasbee7,
) {
    val ctx = LocalContext.current

    CustomDialog(
        title = stringResource(R.string.delete_title),
        showState = showState,
        confirmText = stringResource(R.string.delete),
        dismissText = stringResource(R.string.keep),
        onConfirm = {
            Toast.makeText(ctx, "Deleting..", Toast.LENGTH_SHORT).show()
            tasabee7ViewModel.deleteTasbee7(currentTasbee7)
            showState.value = false
        }
    ) {
        Text(stringResource(R.string.delete_message, currentTasbee7.text))
    }
}
