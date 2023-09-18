package com.example.compostablebutton.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CompostPileState(
    val id: String,
    val name: String,
    val initialPercentFull: Int = 23
) {
    var percentFull: Int by mutableStateOf(initialPercentFull)
}