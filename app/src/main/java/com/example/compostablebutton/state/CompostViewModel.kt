package com.example.compostablebutton.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CompostViewModel : ViewModel() {
    /**
     * Don't expose the mutable list of tasks from outside the ViewModel.
     * Instead define _tasks and tasks. _tasks is internal and mutable inside the ViewModel.
     * tasks is public and read-only.
     */
    private val _piles = getCompostPiles().toMutableStateList()
    val piles: List<CompostPileState>
        get() = _piles

    fun getPileOrNull(pileId: String): CompostPileState? = _piles.find { it.id == pileId }

    fun remove(item: CompostPileState) {
        _piles.remove(item)
    }

    fun changePilePercentFull(item: CompostPileState, percentChange: Int) =
        _piles.find { it.id == item.id }?.let { pile ->
            pile.percentFull += percentChange
            pile.percentFull = pile.percentFull.coerceIn(0, 100)
        }

    init {
        viewModelScope.launch {
            while (true) {
                delay((1000L..3000L).random())
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
