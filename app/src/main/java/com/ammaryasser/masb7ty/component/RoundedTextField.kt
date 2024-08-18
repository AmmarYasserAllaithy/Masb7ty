package com.ammaryasser.masb7ty.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RoundedTextField(
    placeholder: String,
    valueState: MutableState<String>,
    isNumberType: Boolean = false,
    radius: Dp = 12.dp,
    supportingText: @Composable (() -> Unit)? = null,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        placeholder = {
            Text(placeholder)
        },
        keyboardOptions = KeyboardOptions(
            //imeAction = ImeAction.Next,
            keyboardType = if (isNumberType) KeyboardType.Number else KeyboardType.Text,
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        supportingText = supportingText,
        textStyle = TextStyle(textDirection = TextDirection.Content),
        shape = RoundedCornerShape(radius),
        singleLine = isNumberType
    )
}
