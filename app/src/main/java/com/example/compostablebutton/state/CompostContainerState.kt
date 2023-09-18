package com.example.compostablebutton.state

import androidx.compose.ui.graphics.Color

enum class CompostContainerState(
    val containerColor: Color,
    val outlineColor: Color,
    val nameColor: Color,
    val valueColor: Color
) {

    // between 0% and 50% of compost
    Loading(
        Color(0xffFFFFFF),
        Color(0xffE1E1E5),
        Color(0xff03184F),
        Color(0xff073BC5)
    ),

    // between 50% and 90% of compost
    Optimal(
        Color(0xff109877),
        Color(0xff007054),
        Color(0xffFFFFFF),
        Color(0xffFFFFFF)
    ),

    // more than 90% of compost
    Full(
        Color(0xffF0F0F2),
        Color(0xffE1E1E5),
        Color(0xffB4B4BF),
        Color(0xffB4B4BF)
    )

}

fun CompostPileState.toContainerState(): CompostContainerState = when {
    percentFull < 50 -> CompostContainerState.Loading
    percentFull < 90 -> CompostContainerState.Optimal
    else -> CompostContainerState.Full
}