package com.example.compostablebutton.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CompostPile(
    val id: String,
    val name: String
) {
    var percentFull: Int by mutableStateOf(0)

    var percentDecay: Int by mutableStateOf(0)

    internal fun simulateDecay(deltaDecay: Int) {
        percentDecay += deltaDecay
        percentFull -= deltaDecay
        percentFull = percentFull.coerceIn(0, 100)
    }
}