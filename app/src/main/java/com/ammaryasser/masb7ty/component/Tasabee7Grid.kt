package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammaryasser.masb7ty.data.Tasbee7


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Tasabee7Grid(
    tasabee7: List<Tasbee7>,
    modifier: Modifier = Modifier,
    onClickTasbee7: (Int) -> Unit,
    onEditTasbee7: (Int) -> Unit,
    onDeleteTasbee7: (Int) -> Unit,
    stats: @Composable () -> Unit,
) {

    val gap = 12.dp

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 220.dp),
        modifier = modifier,
        contentPadding = PaddingValues(gap),
        verticalItemSpacing = gap,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {

        item {
            stats()
        }

        items(items = tasabee7, key = { it.id }) {
            it.run {
                Tasbee7SwipeCard(
                    text = text,
                    count = count,
                    target = target,
                    onEdit = {
                        onEditTasbee7(id)
                    },
                    onDelete = {
                        onDeleteTasbee7(id)
                    },
                    onClick = {
                        onClickTasbee7(id)
                    },
                )
            }
        }

    }

}
