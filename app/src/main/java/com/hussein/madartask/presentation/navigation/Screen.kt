package com.hussein.madartask.presentation.navigation

const val USER_ID_ARG = "userId"

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Profile: Screen(route = "profile_screen/{$USER_ID_ARG}") {
        fun passId(id: Int) = "profile_screen/$id"
    }
}