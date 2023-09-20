package com.example.compostablebutton.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class PileState(
    val id: String,
    val name: String
) {
    var percentFull: Int by mutableStateOf(0)

    var percentDecay: Int by mutableStateOf(0)
}