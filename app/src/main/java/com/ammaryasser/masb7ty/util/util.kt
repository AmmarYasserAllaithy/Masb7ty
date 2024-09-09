package com.ammaryasser.masb7ty.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Locale


val roundedShape = RoundedCornerShape(16.dp)


fun vibrateOneShot(ctx: Context, millis: Long) {
    val vibrator = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        vibrator.run {
            cancel()
            vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE))
        }
}


@Composable
fun ArabLocale(isAr: Boolean = true) {
    val locale = Locale(if (isAr) "ar" else "en")
    val configuration = LocalConfiguration.current
    val resources = LocalContext.current.resources

    Locale.setDefault(locale)
    configuration.setLocale(locale)

    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
    //	createConfigurationContext(configuration)

    resources.updateConfiguration(configuration, resources.displayMetrics)
}


@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation

        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}


fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
