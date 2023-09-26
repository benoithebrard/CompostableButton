package com.example.compostablebutton.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CompostViewModel : ViewModel() {

    private val _piles = createPiles().toMutableStateList()

    fun getPileOrNull(pileId: String): PileState? {
        return _piles.find { it.id == pileId }
    }

    fun changePilePercent(scope: CoroutineScope, pileId: String, percentDelta: Int) {
        getPileOrNull(pileId)?.changePercentFullBy(scope, percentDelta)
    }

    init {
        viewModelScope.launch {
            while (true) {
                delay((10000L..15000L).random())
                simulateDecay(this)
            }
        }
    }

    private fun simulateDecay(scope: CoroutineScope) {
        _piles.forEach { pile ->
            val deltaDecay = (5..20).random().takeIf { decay ->
                pile.percentFull > decay
            } ?: pile.percentFull

            pile.apply {
                percentDecay += deltaDecay
                changePercentFullBy(scope, -deltaDecay)
            }
        }
    }

    private fun createPiles() = listOf(
        PileState("0", "apples"),
        PileState("1", "bananas"),
        PileState("2", "kiwis"),
        PileState("3", "bread"),
        PileState("4", "coffee")
    )
}
