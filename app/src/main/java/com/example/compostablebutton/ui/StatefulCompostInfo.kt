package com.example.compostablebutton.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compostablebutton.state.toContainerState
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun StatefulCompostInfo(
    pileId: String = "0"
) {
    StatefulPile(pileId) { pile ->
        val containerState = pile.toContainerState()
        CompostInfo(
            totalSoil = pile.percentDecay * containerState.decayScaling * 40 / 100,
            totalGas = pile.percentDecay * containerState.decayScaling * 60 / 100
        )
    }
}

@Preview(showBackground = true, widthDp = 150)
@Composable
fun StatefulInfoPreview() {
    CompostableButtonTheme {
        StatefulCompostInfo()
    }
}