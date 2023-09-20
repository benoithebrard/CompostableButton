package com.example.compostablebutton.state

import androidx.compose.ui.graphics.Color

enum class ContainerState(
    val containerColor: Color,
    val outlineColor: Color,
    val nameColor: Color,
    val valueColor: Color,
    val decayScaling: Int
) {

    // add more compost
    Loading(
        Color(0xffFFFFFF),
        Color(0xffE1E1E5),
        Color(0xff03184F),
        Color(0xff073BC5),
        1
    ),

    // perfect balance
    Optimal(
        Color(0xff109877),
        Color(0xff007054),
        Color(0xffFFFFFF),
        Color(0xffFFFFFF),
        2
    ),

    // stop adding compost
    Full(
        Color(0xffF0F0F2),
        Color(0xffE1E1E5),
        Color(0xffB4B4BF),
        Color(0xffB4B4BF),
        1
    )

}

fun PileState.toContainerState(): ContainerState = when {
    percentFull < 65 -> ContainerState.Loading
    percentFull < 85 -> ContainerState.Optimal
    else -> ContainerState.Full
}