package com.example.compostablebutton.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class PileState(
    val id: String,
    val name: String
) {
    var percentFull: Int by mutableStateOf(0)
        private set

    fun changePercentFullBy(deltaPercent: Int) {
        val previousPercentFull = percentFull
        percentFull += deltaPercent
        percentFull = percentFull.coerceIn(0, 100)
        percentFullState = when {
            percentFull > previousPercentFull -> PercentFullState.Increasing
            percentFull < previousPercentFull -> PercentFullState.Decreasing
            else -> PercentFullState.Default
        }
    }

    var percentFullState: PercentFullState by mutableStateOf(PercentFullState.Default)

    var percentDecay: Int by mutableStateOf(0)
}