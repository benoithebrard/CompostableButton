package com.example.compostablebutton.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CompostViewModel : ViewModel() {

    private val _piles = createPiles().toMutableStateList()
    val piles: List<CompostPile>
        get() = _piles

    fun getPileOrNull(pileId: String): CompostPile? {
        return _piles.find { it.id == pileId }
    }

    fun changePilePercent(pileId: String, percentDelta: Int) {
        getPileOrNull(pileId)?.let { pile ->
            pile.percentFull += percentDelta
            pile.percentFull = pile.percentFull.coerceIn(0, 100)
        }
    }

    init {
        viewModelScope.launch {
            while (true) {
                delay((2000L..5000L).random())
                simulateDecay()
            }
        }
    }

    private fun simulateDecay() {
        _piles.forEach { pile ->
            val deltaDecay = (5..20).random()
            pile.simulateDecay(deltaDecay)
        }
    }

    private fun createPiles() = listOf(
        CompostPile("0", "apples"),
        CompostPile("1", "bananas"),
        CompostPile("2", "kiwis"),
        CompostPile("3", "bread"),
        CompostPile("4", "coffee")
    )
}
