package com.example.compostablebutton.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun CompostPile(
    modifier: Modifier = Modifier,
    pileId: String = "0"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        StatefulCompostButton(modifier.width(150.dp), pileId)
        Spacer(Modifier.width(4.dp))
        StatefulCompostInfo(modifier, pileId)
    }

}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun StatefulPilePreview() {
    CompostableButtonTheme {
        CompostPile()
    }
}