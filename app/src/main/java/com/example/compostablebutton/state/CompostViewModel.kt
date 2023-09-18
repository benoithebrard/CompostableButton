package com.example.compostablebutton.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CompostViewModel : ViewModel() {

    private val _piles = getCompostPiles().toMutableStateList()
    val piles: List<CompostPileState>
        get() = _piles

    fun removePile(pile: CompostPileState) {
        _piles.remove(pile)
    }

    fun getPileOrNull(pileId: String): CompostPileState? {
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
                _piles.forEach { pile ->
                    pile.percentFull -= (5..20).random()
                    pile.percentFull = pile.percentFull.coerceIn(0, 100)
                }
            }
        }
    }
}

private fun getCompostPiles() = listOf(
    CompostPileState("0", "apples"),
    CompostPileState("1", "bananas"),
    CompostPileState("2", "kiwis"),
    CompostPileState("3", "bread"),
    CompostPileState("4", "coffee")
)
