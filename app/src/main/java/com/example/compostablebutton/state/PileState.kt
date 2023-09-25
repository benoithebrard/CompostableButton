package com.example.compostablebutton.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val MAX_ANIMATION_TIME_MS = 5000L

data class PileState(
    val id: String,
    val name: String
) {
    var percentFull: Int by mutableStateOf(0)
        private set

    var percentFullState: PercentFullState by mutableStateOf(PercentFullState.Default)

    var percentDecay: Int by mutableStateOf(0)

    private var lastAnimationResetJob: Job? by mutableStateOf(null)

    fun changePercentFullBy(scope: CoroutineScope, deltaPercent: Int) {
        val previousPercentFull = percentFull
        percentFull += deltaPercent
        percentFull = percentFull.coerceIn(0, 100)
        percentFullState = updatePercentFullState(scope, previousPercentFull)
    }

    private fun updatePercentFullState(
        scope: CoroutineScope,
        previousPercentFull: Int
    ): PercentFullState {
        return when {
            percentFull > previousPercentFull -> PercentFullState.Increasing
            percentFull < previousPercentFull -> PercentFullState.Decreasing
            else -> null
        }?.also {
            lastAnimationResetJob?.cancel()
            lastAnimationResetJob = scope.launch {
                delay(MAX_ANIMATION_TIME_MS)
                percentFullState = PercentFullState.Default
            }
        } ?: PercentFullState.Default
    }
}