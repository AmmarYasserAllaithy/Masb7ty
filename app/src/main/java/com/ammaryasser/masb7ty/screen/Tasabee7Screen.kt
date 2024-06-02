package com.ammaryasser.masb7ty.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.component.CustomDialog
import com.ammaryasser.masb7ty.component.RoundedTextField
import com.ammaryasser.masb7ty.component.Tasbee7SwipeCard
import com.ammaryasser.masb7ty.component.TopBar
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.util.rocoshape
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


private lateinit var vm: Tasabee7ScreenViewModel


@Composable
fun Tasabee7Screen(
    onNavToMasba7tyScreen: (Int) -> Unit,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit
) {
    vm = viewModel(factory = Tasabee7ScreenViewModel.Factory)

    Column(Modifier.fillMaxSize()) {
        val tasabee7 = vm.tasabee7.observeAsState()

        var total by remember { mutableIntStateOf(0) }
        var totalCount by remember { mutableIntStateOf(0) }
        var totalTarget by remember { mutableIntStateOf(0) }

        Tasabee7TopBar(total, onNavToAboutScreen, onNavBack)

        tasabee7.value?.run {
            total = count()
            totalCount = 0
            totalTarget = 0

            forEach {
                totalCount += it.count
                totalTarget += it.target
            }

            Tasabee7Grid(this, Modifier.weight(1f), onNavBack) {
                Text(
                    text = stringResource(R.string.stats_phrase, totalCount, totalTarget),
                    color = colorScheme.secondary.copy(.75f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}


@Composable
fun Tasabee7TopBar(
    total: Int,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit
) {
    TopBar(
        stringResource(R.string.tasabee7) + " ($total)",
        true,
        onNavBack
    ) {
        var showAddTasbee7Dialog by remember { mutableStateOf(false) }
        var showUnifyTargetDialog by remember { mutableStateOf(false) }
        var isMenuExpanded by remember { mutableStateOf(false) }

        val hideMenu = { isMenuExpanded = false }

        IconButton(onClick = { showAddTasbee7Dialog = true }) {
            Icon(Outlined.Add, "", tint = colorScheme.onPrimary)
        }

        IconButton(onClick = { isMenuExpanded = true }) {
            Icon(Outlined.MoreVert, "", tint = colorScheme.onPrimary)
        }


        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = hideMenu
        ) {
            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.clear_all_counters))
                },
                leadingIcon = {
                    Icon(Outlined.Refresh, "")
                },
                onClick = {
                    vm.clearAllCounters()
                    hideMenu()
                })

            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.unify_all_targets))
                },
                leadingIcon = {
                    Icon(Outlined.Create, contentDescription = "")
                },
                onClick = {
                    hideMenu()
                    showUnifyTargetDialog = true
                })

            HorizontalDivider(Modifier.height(1.dp))

            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.about))
                },
                leadingIcon = {
                    Icon(Outlined.Info, "")
                },
                onClick = {
                    hideMenu()
                    onNavToAboutScreen()
                })
        }


        if (showAddTasbee7Dialog) AddNewTasbee7Dialog { showAddTasbee7Dialog = false }


        var unifiedTarget by remember { mutableStateOf("") }

        if (showUnifyTargetDialog)
            CustomDialog(
                title = stringResource(R.string.unify_all_targets),
                onDismiss = {
                    showUnifyTargetDialog = false
                },
                onConfirm = {
                    vm.unifyAllTargets(unifiedTarget.toInt())

                    showUnifyTargetDialog = false
                    unifiedTarget = ""
                },
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Outlined.Info, "")
                        Text(stringResource(R.string.unify_all_targets_note))
                    }

                    RoundedTextField(
                        stringResource(R.string.unified_target),
                        unifiedTarget,
                        true
                    ) { unifiedTarget = it }
                }
            }

    }
}


@Composable
fun AddNewTasbee7Dialog(
    tasbee7: Tasbee7? = null,
    onDismiss: () -> Unit
) {
    var showInputDialog by remember { mutableStateOf(true) }
    var inputText by remember { mutableStateOf("") }
    var inputTarget by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        tasbee7?.run {
            inputText = text
            inputTarget = target.toString()
        }
    }

    if (showInputDialog)
        CustomDialog(
            title = stringResource(if (tasbee7 == null) R.string.add_tasbee7 else R.string.edit_tasbee7),
            onDismiss = {
                showInputDialog = false
                onDismiss()
            },
            onConfirm = {
                val target = inputTarget.trim().toInt()

                val save = { text: String ->
                    text.trim()
                        .takeUnless(String::isEmpty)
                        ?.run {
                            tasbee7?.also {
                                it.text = this
                                it.target = target
                                vm.saveTasbee7(it)

                                onDismiss()
                            }
                                ?: vm.saveTasbee7(Tasbee7(text = this, target = target))
                        }
                }

                inputText.trim()
                    .takeUnless(String::isEmpty)
                    ?.run {
                        if (matches(Regex("(.*\n.*)+")))
                            split("\n").forEach { save(it) }
                        else save(this)
                    }

                //showInputDialog = false
                inputText = ""
                inputTarget = ""
            },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RoundedTextField(stringResource(R.string.tasbee7_text), inputText) {
                    inputText = it
                }

                RoundedTextField(stringResource(R.string.target), inputTarget, true) {
                    inputTarget = it
                }
            }
        }
}


@Composable
fun Tasabee7Grid(
    tasabee7: List<Tasbee7>,
    modifier: Modifier = Modifier,
    onNavBack: () -> Unit,
    stats: @Composable () -> Unit
) {
    val gap = 12.dp
    val ctx = LocalContext.current

    var current by remember { mutableStateOf(Tasbee7(text = "", target = 0)) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }


    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 220.dp),
        modifier = modifier,
        contentPadding = PaddingValues(gap),
        verticalItemSpacing = gap,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        items(
            items = tasabee7,
            key = { it.id }
        ) {
            it.run {
                Tasbee7SwipeCard(
                    text,
                    count,
                    target,
                    onEdit = {
                        current = this
                        showEditDialog = true
                    },
                    onDelete = {
                        current = this
                        showDeleteDialog = true
                    }
                ) {
                    tasbee7Id.value = id
                    onNavBack()
                }
            }
        }

        item { stats() }
    }


    if (showEditDialog) AddNewTasbee7Dialog(current) { showEditDialog = false }

    if (showDeleteDialog)
        CustomDialog(
            title = stringResource(R.string.delete_title),
            confirmText = stringResource(R.string.delete),
            dismissText = stringResource(R.string.keep),
            onDismiss = {
                showDeleteDialog = false
            },
            onConfirm = {
                Toast.makeText(ctx, "Deleting..", Toast.LENGTH_SHORT).show()
                vm.deleteTasbee7(current)
                showDeleteDialog = false
            },
        ) {
            Text(stringResource(R.string.delete_message, current.text))
        }

}