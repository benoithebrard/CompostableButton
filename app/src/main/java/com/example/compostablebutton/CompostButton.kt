package com.example.compostablebutton

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compostablebutton.ui.state.ContainerState
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun CompostButton(
    containerState: ContainerState = ContainerState.Loading,
    compostType: String = "apples",
    percentFull: Float = 0f
) {
    val contextForToast = LocalContext.current.applicationContext

    OutlinedButton(
        onClick = {
            Toast.makeText(contextForToast, "Added $compostType to compost", Toast.LENGTH_SHORT)
                .show()
        },
        enabled = containerState != ContainerState.Empty,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerState.containerColor,
            disabledContainerColor = ContainerState.Empty.containerColor
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
        )
    ) {
        Text(
            text = "Click Me",
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompostableButtonTheme {
        CompostButton()
    }
}

@Preview(showBackground = true)
@Composable
fun ContainerEmptyPreview() {
    CompostableButtonTheme {
        CompostButton(containerState = ContainerState.Empty)
    }
}

@Preview(showBackground = true)
@Composable
fun ContainerFullPreview() {
    CompostableButtonTheme {
        CompostButton(containerState = ContainerState.Full)
    }
}