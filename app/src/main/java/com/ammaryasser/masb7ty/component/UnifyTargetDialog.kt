package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
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
fun UnifyTargetDialog(
    isAppeared: Boolean,
    onDismiss: () -> Unit,
    onUnify: (Int) -> Unit,
) {

    var unifiedTarget by remember { mutableStateOf("") }

    CustomDialog(
        title = stringResource(R.string.unify_all_targets),
        isAppeared = isAppeared,
        onDismiss = onDismiss,
        onConfirm = {
            onUnify(unifiedTarget.toInt())
        },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            RoundedTextField(
                placeholder = stringResource(R.string.unified_target),
                value = unifiedTarget,
                onValueChange = {
                    unifiedTarget = it
                },
                isNumberType = true
            ) {
                Text(stringResource(R.string.unify_all_targets_note))
            }

        }

    }

}
