package com.ammaryasser.masb7ty.viewmodel

import com.ammaryasser.masb7ty.data.Tasbee7


data class AzkarState(

    val loading: Boolean = false,

    val empty: Boolean = false,

    val azkar: List<Tasbee7> = listOf(),
)
