package com.example.quizmemes.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizmemes.data.emptities.Question
import com.example.quizmemes.viewModel.ViewModel
import com.example.quizmemes.ui.navigation.Screens

@Composable
fun QuestionView(navController: NavController) {
    val viewModel: ViewModel = viewModel()

    val question = viewModel.question.observeAsState()

    if (question.value == null) {
        viewModel.getQuestion()
    } else {
        CardQuestion(
            question = question.value!!,
            showResult = {
                navController.currentBackStackEntry?.arguments?.putBoolean("isRight", it)
                navController.navigate(Screens.ResultScreens.route)
            },
            skip = { viewModel.getQuestion() }
        )
    }
}

@Composable
fun CardQuestion(question: Question, skip: () -> Unit, showResult: (isRight: Boolean) -> Unit) {
    Card(modifier = Modifier.padding(16.dp), elevation = 4.dp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {

            val textValue = remember { mutableStateOf("") }

            question.text?.let { Text(text = it, style = MaterialTheme.typography.h5) }
            OutlinedTextField(
                value = textValue.value,
                onValueChange = { textValue.value = it },
                label = { Text(text = "answer") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Row {
                Button(
                    onClick = { showResult(question.answer == textValue.value) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "send", fontSize = 18.sp)
                }

                Button(onClick = { skip() }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "skip", fontSize = 18.sp)
                }
            }
        }
    }
}