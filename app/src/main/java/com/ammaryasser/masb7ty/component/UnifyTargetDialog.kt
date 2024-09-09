package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun UnifyTargetDialog(showState: MutableState<Boolean>, viewModel: Tasabee7ScreenViewModel) {

    val unifiedTargetState = remember { mutableStateOf("") }

    CustomDialog(
        title = stringResource(R.string.unify_all_targets),
        showState = showState,
        onConfirm = {
            viewModel.unifyAllTargets(unifiedTargetState.value.toInt())

            showState.value = false
            unifiedTargetState.value = ""
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RoundedTextField(
                placeholder = stringResource(R.string.unified_target),
                valueState = unifiedTargetState,
                isNumberType = true
            ) {
                Text(stringResource(R.string.unify_all_targets_note))
            }
        }
    }
}
