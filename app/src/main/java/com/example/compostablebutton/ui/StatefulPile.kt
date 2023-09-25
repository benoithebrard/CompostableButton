package com.example.compostablebutton.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compostablebutton.state.CompostViewModel
import com.example.compostablebutton.state.PileState

@Composable
fun StatefulPile(
    pileId: String = "0",
    viewModel: CompostViewModel = viewModel(),
    content: @Composable (PileState) -> Unit
) {
    viewModel.getPileOrNull(pileId)?.let { pile ->
        content(pile)
    } ?: Text(text = "something bad happened..")
}