package com.ammaryasser.masb7ty.component

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.data.Tasbee7


@Composable
fun DeleteTasbee7Dialog(
    tasbee7: Tasbee7,
    onDismiss: () -> Unit,
    onDeleteItem: () -> Unit,
) {
    val ctx = LocalContext.current

    CustomDialog(
        title = stringResource(R.string.delete_title),
        confirmText = stringResource(R.string.delete),
        dismissText = stringResource(R.string.keep),
        onDismiss = onDismiss,
        onConfirm = {
            Toast.makeText(ctx, "Deleting..", Toast.LENGTH_SHORT).show()
            onDeleteItem()
        }
    ) {
        Text(stringResource(R.string.delete_message, tasbee7.text))
    }
}
