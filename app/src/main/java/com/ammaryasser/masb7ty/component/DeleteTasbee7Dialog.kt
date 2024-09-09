package com.ammaryasser.masb7ty.component

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


@Composable
fun DeleteTasbee7Dialog(
    showState: MutableState<Boolean>,
    tasbee7: Tasbee7,
    viewModel: Tasabee7ScreenViewModel,
) {
    val ctx = LocalContext.current

    CustomDialog(
        title = stringResource(R.string.delete_title),
        showState = showState,
        confirmText = stringResource(R.string.delete),
        dismissText = stringResource(R.string.keep),
        onConfirm = {
            Toast.makeText(ctx, "Deleting..", Toast.LENGTH_SHORT).show()
            viewModel.deleteTasbee7(tasbee7)
            showState.value = false
        }
    ) {
        Text(stringResource(R.string.delete_message, tasbee7.text))
    }
}
