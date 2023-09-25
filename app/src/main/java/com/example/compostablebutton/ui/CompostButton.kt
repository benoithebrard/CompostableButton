package com.example.compostablebutton.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compostablebutton.state.ContainerState
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun CompostButton(
    modifier: Modifier = Modifier,
    name: String = "apples",
    percentFull: Int = 0,
    containerState: ContainerState = ContainerState.Loading,
    onAddCompost: () -> Unit = {}
) {
    val context = LocalContext.current
    var toast: Toast? by rememberSaveable { mutableStateOf(null) }
    var savedPercentFull by remember { mutableStateOf(0) }

    when {
        percentFull > savedPercentFull -> {
            toast?.cancel()
            toast =
                Toast.makeText(context, "$savedPercentFull >> $percentFull", Toast.LENGTH_SHORT)
            toast?.show()
        }
        percentFull < savedPercentFull -> {
            toast?.cancel()
            toast =
                Toast.makeText(
                    context,
                    "$percentFull << $savedPercentFull",
                    Toast.LENGTH_SHORT
                )
            toast?.show()
        }
        else -> {}
    }

    savedPercentFull = percentFull

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
            Spacer(modifier = Modifier.size(4.dp))
            Box {
                Text(
                    text = "$percentFull%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = containerState.valueColor
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 150)
@Composable
fun DefaultPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 56
        )
    }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
fun ContainerEmptyPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 13
        )
    }
}

@Preview(showBackground = true, widthDp = 250)
@Composable
fun ContainerFullPreview() {
    CompostableButtonTheme {
        CompostButton(
            percentFull = 87
        )
    }
}