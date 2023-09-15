package com.example.compostablebutton.ui.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ContainerState(val containerColor: Color, val outlineColor: Color, val elevation: Dp) {

    // less than 30% of compost left in container
    Empty(
        Color(0xffF0F0F2),
        Color(0xffE1E1E5),
        0.dp
    ),

    // between 30% and 70% of compost in container
    Loading(
        Color(0xffFFFFFF),
        Color(0xffE1E1E5),
        3.dp
    ),

    // between 70% of compost and full container
    Full(
        Color(0xff109877),
        Color(0xff007054),
        0.dp
    )

}