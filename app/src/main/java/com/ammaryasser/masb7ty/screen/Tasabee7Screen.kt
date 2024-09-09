package com.ammaryasser.masb7ty.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.masb7ty.R
import com.ammaryasser.masb7ty.component.AddOrEditTasbee7Dialog
import com.ammaryasser.masb7ty.component.DeleteTasbee7Dialog
import com.ammaryasser.masb7ty.component.Tasabee7ScreenTopBar
import com.ammaryasser.masb7ty.component.Tasbee7SwipeCard
import com.ammaryasser.masb7ty.data.Tasbee7
import com.ammaryasser.masb7ty.viewmodel.Tasabee7ScreenViewModel


lateinit var tasabee7ViewModel: Tasabee7ScreenViewModel


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7Screen(
    onNavToMasba7tyScreen: (Int) -> Unit,
    onNavToAboutScreen: () -> Unit,
    onNavBack: () -> Unit,
) {
    tasabee7ViewModel = viewModel(factory = Tasabee7ScreenViewModel.Factory)

    Column(
        Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        val tasabee7 = tasabee7ViewModel.tasabee7.collectAsState()
        val total by derivedStateOf { mutableIntStateOf(tasabee7.value.count()) }
        val totalCount by derivedStateOf { mutableIntStateOf(tasabee7.value.sumOf { it.count }) }
        val totalTarget by derivedStateOf { mutableIntStateOf(tasabee7.value.sumOf { it.target }) }

        Tasabee7ScreenTopBar(total.intValue, onNavToAboutScreen, onNavBack)

        tasabee7.value.takeIf { it.isNotEmpty() }?.run {
            Tasabee7Grid(
                tasabee7 = this,
                modifier = Modifier.weight(1f),
                onClickTasbee7 = onNavToMasba7tyScreen
            ) {
                if (totalCount.intValue > 0)
                    Text(
                        text = stringResource(
                            R.string.stats_phrase,
                            totalCount.intValue,
                            totalTarget.intValue
                        ),
                        color = colorScheme.secondary.copy(.85f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7Grid(
    tasabee7: List<Tasbee7>,
    modifier: Modifier = Modifier,
    onClickTasbee7: (Int) -> Unit,
    stats: @Composable () -> Unit,
) {
    val gap = 12.dp
    val editDialogShowState = remember { mutableStateOf(false) }
    val deleteDialogShowState = remember { mutableStateOf(false) }
    val currentTasbee7 = remember { mutableStateOf(Tasbee7(text = "--", target = 0)) }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 220.dp),
        modifier = modifier,
        contentPadding = PaddingValues(gap),
        verticalItemSpacing = gap,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        item { stats() }

        items(items = tasabee7, key = { it.id }) {
            it.run {
                Tasbee7SwipeCard(
                    text = text,
                    count = count,
                    target = target,
                    onEdit = {
                        currentTasbee7.value = this
                        println(currentTasbee7.value.text)
                        editDialogShowState.value = true
                    },
                    onDelete = {
                        currentTasbee7.value = this
                        deleteDialogShowState.value = true
                    },
                    onClick = { onClickTasbee7(id) }
                )
            }
        }
    }

    // fixme: pass text and target
    AddOrEditTasbee7Dialog(
        showState = editDialogShowState,
        text = currentTasbee7.value.text,
        target = currentTasbee7.value.target,
    ) { text, target ->
        tasabee7ViewModel.saveTasbee7(currentTasbee7.value.copy(text = text, target = target))
    }

    DeleteTasbee7Dialog(showState = deleteDialogShowState, currentTasbee7 = currentTasbee7.value)

}
