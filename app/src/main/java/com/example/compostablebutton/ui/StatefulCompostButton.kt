/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.compostablebutton.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compostablebutton.state.CompostViewModel
import com.example.compostablebutton.state.toContainerState
import com.example.compostablebutton.ui.theme.CompostableButtonTheme

@Composable
fun StatefulCompostButton(
    modifier: Modifier = Modifier,
    pileId: String = "0",
    viewModel: CompostViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()

    StatefulPile(pileId) { pile ->
        CompostButton(
            modifier = modifier.fillMaxWidth(),
            name = pile.name,
            percentFull = pile.percentFull,
            percentFullState = pile.percentFullState,
            containerState = pile.toContainerState()
        ) {
            val percentAdded = (10..25).random()
            viewModel.changePilePercent(scope, pile.id, percentAdded)
        }
    }
}

@Preview(showBackground = true, widthDp = 150)
@Composable
fun StatefulCompostPreview() {
    CompostableButtonTheme {
        StatefulCompostButton()
    }
}

