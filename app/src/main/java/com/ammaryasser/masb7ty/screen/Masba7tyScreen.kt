package com.ammaryasser.masb7ty.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ammaryasser.masb7ty.component.TopBar
import com.ammaryasser.masb7ty.util.LockScreenOrientation
import com.ammaryasser.masb7ty.util.roundedShape
import com.ammaryasser.masb7ty.util.vibrateOneShot
import com.ammaryasser.masb7ty.viewmodel.Masba7tyScreenViewModel


@Composable
fun Masba7tyScreen(
    zekrId: Int,
    onNavToTasabee7Screen: (Boolean) -> Unit,
) {
    val viewModel: Masba7tyScreenViewModel = viewModel(factory = Masba7tyScreenViewModel.Factory)

    LockScreenOrientation(1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
    ) {
        TopBar {
            IconButton(onClick = { onNavToTasabee7Screen(false) }) {
                Icon(Outlined.Menu, "", tint = colorScheme.onPrimary)
            }
        }

        Masba7ah(zekrId, viewModel, onNavToTasabee7Screen)
    }
}


@Composable
fun Masba7ah(
    zekrId: Int,
    viewModel: Masba7tyScreenViewModel,
    onNavToTasabee7Screen: (Boolean) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {

        val tasabee7 = viewModel.tasabee7.collectAsState()

        var idx by rememberSaveable { mutableIntStateOf(-1) }
        var text by remember { mutableStateOf("") }
        var target by remember { mutableIntStateOf(-1) }
        var count by remember { mutableIntStateOf(-1) }

        LaunchedEffect(idx) {
            tasabee7.value.azkar.takeUnless { it.isEmpty() }?.get(idx)?.let {
                text = it.text
                target = it.target
                count = it.count
            }
        }

        tasabee7.value.run {
            when {
                loading -> {}
                empty -> onNavToTasabee7Screen(true)
                else -> LaunchedEffect(Unit) {
                    if (idx == -1) idx = 0
                    else azkar
                        .indexOfFirst { it.id == zekrId }
                        .takeIf { it > -1 }
                        ?.run { idx = this }
                }
            }
        }

        val saveCount = {
            tasabee7.value.run {
                azkar[idx].let {
                    it.count = count
                    viewModel.saveTasbee7(it)
                }
            }
        }


        Tasbee7Text(text)

        CounterBoard(count, target) {
            count = 0
            saveCount()
        }


        val ctx = LocalContext.current

        val onSlide = { i: Int ->
            tasabee7.value.azkar.run {
                when (idx + i) {
                    size -> {
                        idx = 0
                        vibrateOneShot(ctx, 200)
                    }

                    -1 -> {
                        idx = size - 1
                        vibrateOneShot(ctx, 200)
                    }

                    else -> idx += i
                }
            }
        }


        NavCtrl(
            onPrev = { onSlide(-1) },
            onNext = { onSlide(1) }
        )


        val circleDiameter = 177.dp
        val circleMargin = 16.dp

        Box(
            Modifier
                .fillMaxSize()
                .clip(roundedShape)
                .clickable {
                    count++

                    saveCount()

                    if (count % target == 0) {
                        onSlide(1)
                        vibrateOneShot(ctx, 1000)
                    }
                },
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .padding(circleMargin)
                    .width(circleDiameter)
                    .height(circleDiameter)
                    .clip(RoundedCornerShape(circleDiameter))
                    .background(colorScheme.surface)
            )
        }
    }
}


@Composable
fun Tasbee7Text(text: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 400.dp)
            .padding(top = 8.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)
            .clip(roundedShape)
            .background(colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = text,
            color = colorScheme.onSurface,
            style = typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun CounterBoard(
    count: Int,
    target: Int,
    onReset: () -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .clip(roundedShape)
            .background(colorScheme.surface)
            .padding(vertical = 5.dp, horizontal = 12.dp)
    ) {

        val tint = colorScheme.onSurface.copy(.5f)

        IconButton(
            onClick = onReset,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(Outlined.Refresh, "", tint = tint)
        }

        Text(
            "$count",
            modifier = Modifier.align(Alignment.Center),
            color = tint.copy(.9f),
            style = typography.headlineSmall
        )

        Text(
            "$target",
            modifier = Modifier.align(Alignment.CenterEnd),
            color = tint
        )

    }
}


@Composable
fun NavCtrl(
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(roundedShape),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val modifier = Modifier
            .clip(roundedShape)
            .background(colorScheme.surface)
            .padding(horizontal = 8.dp)
        val tint = colorScheme.onSurface.copy(.5f)

        IconButton(onClick = onPrev, modifier = modifier) {
            Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, "", tint = tint)
        }

        IconButton(onClick = onNext, modifier = modifier) {
            Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, "", tint = tint)
        }

    }
}