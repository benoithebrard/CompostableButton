package com.example.compostablebutton.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun CompostInfo(
    modifier: Modifier = Modifier,
    totalSoil: Int = 0,
    totalGas: Int = 0
) {
    Row {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "$totalSoil m3 soil /",
            fontSize = 11.sp,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = "$totalGas m3 CH4",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Cursive
        )
    }

}

@Preview(showBackground = true, widthDp = 150)
@Composable
fun InfoPreview() {
    CompostableButtonTheme {
        CompostInfo()
    }
}