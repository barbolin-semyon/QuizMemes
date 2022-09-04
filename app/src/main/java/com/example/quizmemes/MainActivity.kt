package com.example.quizmemes

import android.os.Bundle
import android.os.Handler
import android.text.style.QuoteSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizmemes.ui.theme.QuizMemesTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizMemesTheme {

                val viewModel: ViewModel by viewModels()

                val question = viewModel.question.observeAsState()

                if (question.value == null) {
                    viewModel.getQuestion()
                } else {
                    CardQuestion(
                        question = question.value!!,
                        showResult = {},
                        skip = {viewModel.getQuestion() }
                    )
                }

            }
        }
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
