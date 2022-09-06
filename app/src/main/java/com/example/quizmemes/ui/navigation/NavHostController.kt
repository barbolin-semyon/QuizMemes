package com.example.quizmemes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizmemes.ui.features.QuestionView
import com.example.quizmemes.ui.features.result.ResultView
import com.example.quizmemes.ui.navigation.Screens

@Composable
fun NavHostController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.QuestionScreens.route) {
        composable(Screens.QuestionScreens.route) {
            QuestionView(navController)
        }

        composable(Screens.ResultScreens.route) {
            val isRight = navController.currentBackStackEntry?.arguments?.getBoolean("isRight")
            isRight?.let {
                    it1 -> ResultView(navController = navController, isRight = it1) }
        }
    }
}