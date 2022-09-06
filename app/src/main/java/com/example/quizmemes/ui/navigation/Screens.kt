package com.example.quizmemes.ui.navigation

sealed class Screens(val route: String) {
    object QuestionScreens: Screens(route = "question")
    object ResultScreens: Screens(route = "result")
}
