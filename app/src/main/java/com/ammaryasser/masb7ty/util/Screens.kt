package com.ammaryasser.masb7ty.util

import com.ammaryasser.masb7ty.util.ScreensNavArgs.ID_KEY


sealed class Screens(val route: String) {

    data object Tasabee7 : Screens("tasabee7_screen")

    data class Masba7ty(val id: String = "{$ID_KEY}") : Screens("masba7ty_screen/$id") {
        constructor(id: Int) : this("$id")
    }

    data object About : Screens("about_screen")

}


object ScreensNavArgs {

    const val ID_KEY = "id"

}
