package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.R


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AddOrEditTasbee7Dialog(
    isAppeared: Boolean,
    initialText: String,
    initialTarget: String,
    onDismiss: () -> Unit,
    onConfirm: (String, Int) -> Unit,
) {

    var text by remember { mutableStateOf(initialText) }
    var target by remember { mutableStateOf(initialTarget) }

    CustomDialog(
        title = stringResource(if (initialText.isBlank()) R.string.add_tasbee7 else R.string.edit_tasbee7),
        isAppeared = isAppeared,
        onDismiss = onDismiss,
        onConfirm = {
            arrayOf(text, target)
                .map { it.trim() }
                .takeIf { it.all { it.isNotBlank() } }
                ?.run {
                    onConfirm(this[0], this[1].toInt())
                }
        },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            RoundedTextField(
                placeholder = stringResource(R.string.tasbee7_text),
                value = text,
                onValueChange = {
                    text = it
                },
            )

            RoundedTextField(
                placeholder = stringResource(R.string.target),
                value = target,
                onValueChange = {
                    target = it
                },
                isNumberType = true,
            )

        }

    }

}
