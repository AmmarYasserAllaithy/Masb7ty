package com.ammaryasser.masb7ty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ammaryasser.masb7ty.ui.theme.Masb7tyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Masb7tyTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}