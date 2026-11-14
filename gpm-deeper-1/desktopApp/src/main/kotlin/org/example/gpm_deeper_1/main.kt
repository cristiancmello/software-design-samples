package org.example.gpm_deeper_1

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Simple Plain Text Editor",
        resizable = false,
        state = rememberWindowState(width = 800.dp, height = 625.dp),
    ) {
        App()
    }
}