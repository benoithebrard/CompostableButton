package com.example.compostablebutton.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compostablebutton.R
import com.example.compostablebutton.state.ContainerState
import com.example.compostablebutton.state.PercentFullState
import com.example.compostablebutton.ui.theme.CompostableButtonTheme
import kotlin.math.roundToInt

@Composable
fun CompostButton(
    modifier: Modifier = Modifier,
    name: String = "apples",
    percentFull: Int = 0,
    containerState: ContainerState = ContainerState.Loading,
    percentFullState: PercentFullState = PercentFullState.Default,
    onAddCompost: () -> Unit = {}
) {
    val valueColor = when (percentFullState) {
        PercentFullState.Default -> null
        PercentFullState.Increasing -> Color(0xFF109877)
        PercentFullState.Decreasing -> Color(0xFFD03A3A)
    }?.takeIf {
        containerState == ContainerState.Loading
    } ?: containerState.valueColor

    val pxToMove = with(LocalDensity.current) {
        4.dp.toPx().roundToInt()
    }

    val decreaseOffset by animateIntOffsetAsState(
        targetValue = if (percentFullState == PercentFullState.Decreasing) {
            IntOffset(pxToMove, pxToMove)
        } else {
            IntOffset.Zero
        },
        label = "decrease offset",
        animationSpec = repeatable(
            iterations = 6,
            repeatMode = RepeatMode.Reverse,
            animation = tween(500)
        )
    )

    val increaseOffset by animateIntOffsetAsState(
        targetValue = if (percentFullState == PercentFullState.Increasing) {
            IntOffset(pxToMove, -pxToMove)
        } else {
            IntOffset.Zero
        },
        label = "increase offset",
        animationSpec = repeatable(
            iterations = 6,
            repeatMode = RepeatMode.Reverse,
            animation = tween(500)
        )
    )

    val decreaseAlpha by animateFloatAsState(
        targetValue = if (percentFullState == PercentFullState.Decreasing) {
            1f
        } else {
            0f
        },
        label = "decrease alpha",
        animationSpec = repeatable(
            iterations = 6,
            repeatMode = RepeatMode.Reverse,
            animation = tween(500)
        )
    )

    val increaseAlpha by animateFloatAsState(
        targetValue = if (percentFullState == PercentFullState.Increasing) {
            1f
        } else {
            0f
        },
        label = "increase alpha",
        animationSpec = repeatable(
            iterations = 6,
            repeatMode = RepeatMode.Reverse,
            animation = tween(500)
        )
    )

    OutlinedButton(
        enabled = containerState != ContainerState.Full,
        onClick = onAddCompost,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerState.containerColor,
            disabledContainerColor = ContainerState.Full.containerColor
        ),
        shape = RoundedCornerShape(
            size = 4.dp
        ),
        border = BorderStroke(
            width = .5.dp,
            color = containerState.outlineColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier.sizeIn(minHeight = 32.dp),
        contentPadding = PaddingValues(all = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.weight(1f)) {
                Text(
                    text = "Compost $name",
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    color = containerState.nameColor
                )
            }
            Box {
                Text(
                    text = "$percentFull",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = valueColor,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                this@Row.AnimatedVisibility(
                    visible = percentFullState == PercentFullState.Decreasing,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_percent_decrease),
                        contentDescription = "percent decrease",
                        modifier = Modifier
                            .padding(bottom = 4.dp, end = 4.dp)
                            .offset { decreaseOffset }
                            .alpha(decreaseAlpha)
                    )
                }
                this@Row.AnimatedVisibility(
                    visible = percentFullState == PercentFullState.Increasing,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_percent_increase),
                        contentDescription = "percent increase",
                        modifier = Modifier
                            .padding(top = 4.dp, end = 4.dp)
                            .offset { increaseOffset }
                            .alpha(increaseAlpha)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 150)
@Composable
fun DefaultPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 56,
            percentFullState = PercentFullState.Default
        )
    }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
fun ContainerEmptyPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 13,
            percentFullState = PercentFullState.Increasing
        )
    }
}

@Preview(showBackground = true, widthDp = 250)
@Composable
fun ContainerFullPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 87,
            percentFullState = PercentFullState.Decreasing
        )
    }
}