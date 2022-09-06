package com.example.quizmemes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.quizmemes.ui.theme.QuizMemesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizMemesTheme {
                val controller = rememberNavController()
                NavHostController(controller)
            }
        }
    }
}

