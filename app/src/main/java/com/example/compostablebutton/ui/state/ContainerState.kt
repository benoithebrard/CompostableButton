package com.example.compostablebutton.ui.state

import androidx.compose.ui.graphics.Color

enum class ContainerState(val outlineColor: Color) {

    // less than 30% of compost left in container
    Empty(Color(0xffE1E1E5)),

    // between 30% and 70% of compost in container
    Loading(Color(0xffE1E1E5)),

    // between 70% of compost and full container
    Full(Color(0xff007054))

}