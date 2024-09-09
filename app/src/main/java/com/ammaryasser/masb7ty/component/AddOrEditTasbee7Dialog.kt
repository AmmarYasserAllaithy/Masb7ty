package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.R


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AddOrEditTasbee7Dialog(
    showState: MutableState<Boolean>,
    textState: MutableState<String> = remember { mutableStateOf("") },
    targetState: MutableState<String> = remember { mutableStateOf("") },
    onConfirm: (String, Int) -> Unit,
) {
    CustomDialog(
        title = stringResource(if (textState.value.isBlank()) R.string.add_tasbee7 else R.string.edit_tasbee7),
        showState = showState,
        onConfirm = {
            arrayOf(textState, targetState)
                .map { it.value.trim() }
                .run {
                    if (all { it.isNotBlank() }) {
                        onConfirm(this[0], this[1].toInt())
                        showState.value = false
                        textState.value = ""
                        targetState.value = ""
                    }
                }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RoundedTextField(stringResource(R.string.tasbee7_text), textState)
            RoundedTextField(stringResource(R.string.target), targetState, true)
        }
    }
}
