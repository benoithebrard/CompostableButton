package com.example.compostablebutton.state

import androidx.compose.ui.graphics.Color

enum class PercentFullState(val color: Color) {

    Default(Color(0xD03A3A)),

    Increasing(Color(0xD03A3A)),

    Decreasing(Color(0x109877))
}