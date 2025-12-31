package com.ammaryasser.masb7ty.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.component.AddOrEditTasbee7Dialog
import com.ammaryasser.masb7ty.component.DeleteTasbee7Dialog
import com.ammaryasser.masb7ty.component.Tasabee7Grid
import com.ammaryasser.masb7ty.component.Tasabee7ScreenTopBar
import com.ammaryasser.masb7ty.component.UnifyTargetDialog
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7Screen(
    onNavToMasba7tyScreen: (Int) -> Unit,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit,
) {

    val viewModel: Tasabee7ScreenViewModel = viewModel(factory = Tasabee7ScreenViewModel.Factory)

    Column(
        Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {

        val tasabee7 = viewModel.tasabee7.collectAsStateWithLifecycle()

        val total by remember {
            derivedStateOf { tasabee7.value.count() }
        }
        val totalCount by remember {
            derivedStateOf { tasabee7.value.sumOf { it.count } }
        }
        val totalTarget by remember {
            derivedStateOf { tasabee7.value.sumOf { it.target } }
        }

        var currentTasbee7 by remember {
            mutableStateOf<Tasbee7?>(null)
        }

        var isAddEditDialogAppeared by remember { mutableStateOf(false) }
        var isDeleteDialogAppeared by remember { mutableStateOf(false) }
        var isUnifyDialogAppeared by remember { mutableStateOf(false) }


        Tasabee7ScreenTopBar(
            total = total,
            onAddClicked = {
                currentTasbee7 = null
                isAddEditDialogAppeared = true
            },
            onUnifyAllClicked = {
                isUnifyDialogAppeared = true
            },
            onClearAllClicked = {
                viewModel.clearAllCounters()
            },
            onNavToAboutScreen = onNavToAboutScreen,
            onNavBack = onNavBack,
        )


        tasabee7.value.takeIf { it.isNotEmpty() }?.run {
            Tasabee7Grid(
                tasabee7 = this,
                modifier = Modifier.weight(1f),
                onClickTasbee7 = onNavToMasba7tyScreen,
                onEditTasbee7 = { id ->
                    currentTasbee7 = viewModel.tasabee7.value.find { it.id == id } ?: currentTasbee7
                    isAddEditDialogAppeared = true
                },
                onDeleteTasbee7 = { id ->
                    currentTasbee7 = viewModel.tasabee7.value.find { it.id == id } ?: currentTasbee7
                    isDeleteDialogAppeared = true
                },
            ) {
                if (totalCount > 0)
                    Text(
                        text = stringResource(
                            R.string.stats_phrase, totalCount, totalTarget
                        ),
                        color = colorScheme.secondary.copy(.85f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
            }
        }


        if (isAddEditDialogAppeared)
            AddOrEditTasbee7Dialog(
                isAppeared = isAddEditDialogAppeared, // TODO: hoist
                initialText = currentTasbee7?.text.orEmpty(),
                initialTarget = currentTasbee7?.target?.toString() ?: "",
                onDismiss = {
                    isAddEditDialogAppeared = false
                },
            ) { text: String, target: Int ->

                val tasbee7 = currentTasbee7?.copy(text = text, target = target)
                    ?: Tasbee7(text = text, target = target)

                viewModel.saveTasbee7(tasbee7)
                isAddEditDialogAppeared = false

//            val addNew = { text: String, target: String ->
//                val save = { txt: String ->
//                    onSave(txt.trim(), target)
//                }
//
//                if (text.contains(Regex("[\n_]+")))
//                    text.split("___").forEach { save(it) }
//                else save(text)
//            }
            }


        if (isDeleteDialogAppeared)
            DeleteTasbee7Dialog(
                isAppeared = isDeleteDialogAppeared,
                tasbee7 = currentTasbee7!!,
                onDismiss = {
                    isDeleteDialogAppeared = false
                },
            ) {
                viewModel.deleteTasbee7(currentTasbee7!!)
            }


        if (isUnifyDialogAppeared)
            UnifyTargetDialog(
                isAppeared = isUnifyDialogAppeared,
                onDismiss = {
                    isUnifyDialogAppeared = false
                },
            ) {
                viewModel.unifyAllTargets(it)
            }

    }
}
