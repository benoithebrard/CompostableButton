package com.example.compostablebutton.state

import androidx.compose.ui.graphics.Color

enum class CompostContainer(
    val containerColor: Color,
    val outlineColor: Color,
    val nameColor: Color,
    val valueColor: Color
) {

    // less than 30% of compost left in container
    Empty(
        Color(0xffF0F0F2),
        Color(0xffE1E1E5),
        Color(0xffB4B4BF),
        Color(0xffB4B4BF)
    ),

    // between 30% and 70% of compost in container
    Loading(
        Color(0xffFFFFFF),
        Color(0xffE1E1E5),
        Color(0xff03184F),
        Color(0xff073BC5)
    ),

    // between 70% of compost and full container
    Full(
        Color(0xff109877),
        Color(0xff007054),
        Color(0xffFFFFFF),
        Color(0xffFFFFFF)
    )

}