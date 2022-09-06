package com.example.quizmemes.ui.features.result

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizmemes.ui.navigation.Screens
import com.example.quizmemes.viewModel.ViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ResultView(navController: NavController, isRight: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val viewModel: ViewModel = viewModel()

        val urlGIf = viewModel.urlResult.observeAsState()

        if (urlGIf.value == null) {
            viewModel.getUrlGif(isRight = isRight)
        } else {
            GIF(
                url = urlGIf.value!!.image, modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("next")
            }
        }
    }
}

@Composable
fun GIF(url: String, modifier: Modifier) {
    GlideImage(imageModel = url, modifier = modifier)
}